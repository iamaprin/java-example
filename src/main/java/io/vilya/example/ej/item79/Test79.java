package io.vilya.example.ej.item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * @author iamaprin
 * @time 2018年2月1日 下午9:59:16
 */
public class Test79 {

	/**
	 * add() -> notifyElementAdded(--> iterate observers) -> SetObserver.added(-->
	 * remove observer from observers) -> ConcurrentModificationException
	 */
	@Test
	public void test1() {
		ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

		set.addObserver(new SetObserver<Integer>() {
			public void added(ObservableSet<Integer> s, Integer e) {
				System.out.println(e);
				if (e == 23) {
					s.removeObserver(this);
				}
			}
		});

		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}
	
	/**
	 * notifyElementAdded --> lock(observers)
	 * removeObserver --> lock(observers)
	 * -> deadlock
	 */
	@Test
	public void test2() {
		ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

		set.addObserver(new SetObserver<Integer>() {
			public void added(ObservableSet<Integer> s, Integer e) {
				System.out.println(e);
				if (e == 23) {
					ExecutorService exec = Executors.newSingleThreadExecutor();
					try {
						exec.submit(() -> s.removeObserver(this)).get();
					} catch (ExecutionException | InterruptedException ex) {
						throw new AssertionError(ex);
					} finally {
						exec.shutdown();
					}
				}
			}
		});

		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}

}
