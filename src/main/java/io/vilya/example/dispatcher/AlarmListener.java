package io.vilya.example.dispatcher;

/**
 * @author zhukuanxin
 * @time 2017年4月8日 上午8:11:11
 */
public class AlarmListener implements IListener {

	@Override
	public void handle(Object data) {
		
		if (!(data instanceof Alarm)) {
			return;
		}
		
		Alarm alarm = (Alarm) data;
		
		System.out.println(alarm);
		
	}
}
