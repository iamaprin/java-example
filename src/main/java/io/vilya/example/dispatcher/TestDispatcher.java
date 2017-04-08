package io.vilya.example.dispatcher;

/**
 * @author zhukuanxin
 * @time 2017年4月8日 上午8:09:07
 */
public class TestDispatcher {

	public static void main(String[] args) {
		
		IListener alarmListener = new AlarmListener();
		IListener exceptionListener = new ExceptionListener();
		
		Dispacther dispacther = new Dispacther();
		dispacther.addlistener("alarm", alarmListener);
		dispacther.addlistener("exception", exceptionListener);
		
		
		// fire alarm
		Alarm alarm = new Alarm();
		alarm.setTopic("camera");
		alarm.setMessage("wrong");
		
		Message alarmMessage = new Message();
		alarmMessage.setKey("alarm");
		alarmMessage.setData(alarm);
		
		dispacther.dispatch(alarmMessage);
		
		// fire exception
		Message exceptionMessage = new Message();
		exceptionMessage.setKey("exception");
	
		dispacther.dispatch(exceptionMessage);
		
		
		// fire null
		Message nullMessage = new Message();
		nullMessage.setKey("null");
		
		dispacther.dispatch(nullMessage);
		
		
		System.out.println(111);
		
		for (int i = 0; i < 10; i++) {
			dispacther.dispatch(alarmMessage);
		}
	}
	
	
}
