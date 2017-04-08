package io.vilya.example.dispatcher;

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
	
	private ConcurrentHashMap<String, IListener> listeners;
	
	private ExecutorService executors;
	
	{
		listeners = new ConcurrentHashMap<>();
		executors = Executors.newFixedThreadPool(poolSize);
	}
	
	Dispacther() {}	
	
	public void addlistener(String key, IListener listener) {
		Assert.notNull(key, "Argument[key] can not be null.");
		Assert.notNull(listener, "Argument[listener] can not be null.");
		
		if (listeners.contains(key)) {
			throw new DispatchException("Key exist.");
		}
		
		listeners.put(key, listener);
	}
	
	public void dispatch(Message message) {
		Assert.notNull(message, "Argument[message] can not be null.");

		String key = message.getKey();
		Object data = message.getData();
		
		IListener listener = listeners.get(key);
		if (listener == null) {
			LOGGER.info("No listener corresponding to the key: {}", key);
		}
		
		/*
		try {
			listener.handle(data);			
		} catch (Exception e) {
			LOGGER.error("error in listener: ", e);
		}
		*/
		
		// new Thread(new ListenerThread(listener, data)).start();
		
		executors.submit(new ListenerThread(listener, data));
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
