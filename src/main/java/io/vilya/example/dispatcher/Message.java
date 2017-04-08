package io.vilya.example.dispatcher;

/**
 * @author zhukuanxin
 * @time 2017年4月8日 上午7:42:00
 */
public class Message {
	
	private String key;
	
	private Object data;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
