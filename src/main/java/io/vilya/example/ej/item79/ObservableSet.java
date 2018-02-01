package io.vilya.example.ej.item79;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import io.vilya.example.ej.item18.ForwardingSet;

/**
 * @author iamaprin
 * @time 2018年2月1日 下午9:57:30
 */
public class ObservableSet<E> extends ForwardingSet<E> {

	public ObservableSet(Set<E> set) {
		super(set);
	}

	private final List<SetObserver<E>> observers = new ArrayList<>();

	public void addObserver(SetObserver<E> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}

	public boolean removeObserver(SetObserver<E> observer) {
		synchronized (observers) {
			return observers.remove(observer);
		}
	}

	private void notifyElementAdded(E element) {
		synchronized (observers) {
			for (SetObserver<E> observer : observers)
				observer.added(this, element);
		}
	}

	@Override
	public boolean add(E element) {
		boolean added = super.add(element);
		if (added)
			notifyElementAdded(element);
		return added;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (E element : c)
			result |= add(element); // Calls notifyElementAdded
		return result;
	}

}
