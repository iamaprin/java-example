package io.vilya.example.ej.item79;

/**
 * @author iamaprin
 * @time 2018年2月1日 下午9:58:24
 */
@FunctionalInterface
public interface SetObserver<E> {

	void added(ObservableSet<E> set, E element);
	
}
