package io.vilya.example.dispatcher;

/**
 * @author zhukuanxin
 * @time 2017年4月8日 上午8:11:44
 */
public class Alarm {
	
	private String topic;
	
	private String message;

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Alarm [topic=" + topic + ", message=" + message + "]";
	}
	
}
