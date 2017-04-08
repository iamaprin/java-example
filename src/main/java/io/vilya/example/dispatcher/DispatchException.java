package io.vilya.example.dispatcher;

/**
 * @author zhukuanxin
 * @time 2017年4月8日 上午7:49:15
 */
public class DispatchException extends RuntimeException {

	private static final long serialVersionUID = 4218099596546868801L;

	DispatchException(String message) {
		super(message);
	}
}
