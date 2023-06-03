package pkg;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.stream.*;
import java.time.*;

/* 
	Lock/Monitor is used to synchronize access. It enables one thread to access the monitor at a time. 
	The below adds a monitor on any object.
	synchronized(this) { }
	
	This also works.
	private synchronized void methodName() { }
*/

public class ThreadSafeCode {
	public static void main(String... args) throws Exception {
		// atomicClasses();
		
		// lock();
		
		cyclicBarrier();
	}
	
	public static void atomicClasses() {
		/* AtomicBoolean ab = new AtomicBoolean();
		print(ab.get()); //false
		print(ab.getAndSet(true)); //false
		print(ab.get()); //true */
		
		AtomicInteger ai = new AtomicInteger(10);
		print(ai.incrementAndGet()); //11
		print(ai.getAndIncrement()); //11
		print(ai.get()); //12
		print(ai.decrementAndGet()); //11
		
		AtomicLong al = new AtomicLong();
	}
	
	public static void lock() throws InterruptedException{
		Lock lock = new ReentrantLock();
		try {
			// lock.lock();
			if(lock.tryLock(5, TimeUnit.SECONDS))
				print("Lock acquired!");
			// Protected Code.
		} finally {
			lock.unlock();
			print("Unlocked!");
		}
	}
	
	/* A usecase where all the threads have to execute task 1 before task 2 has to be executed. */
	public static void cyclicBarrier() {
		ExecutorService es = null;
		
		try {
			es = Executors.newFixedThreadPool(4);
			var c = new CyclicBarrier(4);
			for (int i = 0; i < 4; i++)
				es.submit(() -> performTask(c));
		} finally {
			if(es != null)
				es.shutdown();
		}
	}
	
	public static void performTask(CyclicBarrier c) {
		print("Performed Task 1");
		
		try {
			c.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

		// Won't execute until 4 tasks call await.
		print("Performed Task 2");
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}