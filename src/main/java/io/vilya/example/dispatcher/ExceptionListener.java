package io.vilya.example.dispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhukuanxin
 * @time 2017年4月8日 上午8:14:23
 */
public class ExceptionListener implements IListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionListener.class);
	
	@Override
	public void handle(Object data) {
		LOGGER.info("in exceptionListener");
		throw new RuntimeException();
	}

}
