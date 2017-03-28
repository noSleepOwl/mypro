
package mytools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i =0 ; i<2;i++)
			exec.execute(new LiftOff());
		exec.shutdown();
	}

}