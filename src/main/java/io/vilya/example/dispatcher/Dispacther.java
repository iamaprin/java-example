package io.vilya.example.dispatcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhukuanxin
 * @time 2017年4月8日 上午7:41:48
 */
public class Dispacther {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Dispacther.class);
	
	private int poolSize = 5;
	
	private ConcurrentHashMap<String, List<IListener>> listenerMap;
	
	private ExecutorService executors;
	
	{
		listenerMap = new ConcurrentHashMap<>();
		executors = Executors.newFixedThreadPool(poolSize);
	}
	
	Dispacther() {}	
	
	public void addlistener(String key, IListener listener) {
		Assert.notNull(key, "Argument[key] can not be null.");
		Assert.notNull(listener, "Argument[listener] can not be null.");
		
		List<IListener> listeners;
		if (listenerMap.containsKey(key)) {
			listeners = listenerMap.get(key);
			listeners.add(listener);
		} else {
			listeners = new LinkedList<>();
			listeners.add(listener);
			
			listenerMap.put(key, listeners);
		}
	}
	
	public void dispatch(Message message) {
		Assert.notNull(message, "Argument[message] can not be null.");

		String key = message.getKey();
		Object data = message.getData();
		
		List<IListener> listeners = listenerMap.get(key);
		if (listeners == null) {
			LOGGER.info("No listener corresponding to the key: {}", key);
			return;
		}
		
		Runnable task;
		for (IListener listener : listeners) {
			task = new ListenerThread(listener, data);
			executors.submit(task);
		}
	}
	
	public void shutdown() {
		executors.shutdown();
	}
	
	private class ListenerThread implements Runnable{

		private IListener listener;
		
		private Object data;
		
		ListenerThread(IListener listener, Object data) {
			this.listener = listener;
			this.data = data;
		}
		
		@Override
		public void run() {
			listener.handle(data);
		}
	}
	
	
	
}
