package pkg;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

class Threads {
	static int counter = 0;
	
	public static void main(String[] args) throws Exception {
		/* 1 - Provide a Runnable object or lambda to the Thread constructor. */
		// new Thread(new PrintData()).start();
		
		/* 2 - Create a class that extends Thread and overrides the run() method. */
		// new PrintThread().start();
		
		// checkResults();
		
		// singleThreadExecutorWithExecute();
		
		// singleThreadExecutorWithSubmit();
		
		// singleThreadExecutorWithSubmitAndCallable(); //48
		
		// singleThreadExecutorWithInvokeAll();
		
		singleThreadExecutorWithInvokeAny();
	}
	
	public static void checkResults() throws InterruptedException {
				
		new Thread(() -> {
			for (int i = 0; i < 500; i++)
				Threads.counter++;
		}).start();
		
		while(counter < 500){
			print("Not reached yet!");
			/* Helps potentially stop the CPU resources being locked up. */
			Thread.sleep(1000);
		}
		
		print(counter + " : Finished!");
	}
	
	public static void singleThreadExecutorWithExecute() {
		ExecutorService es = null;
		Runnable task = () -> Stream.iterate(1, n -> n < 1000, n -> n + 1).forEach(System.out::println);
		
		/* The above specified task is executed sequentially. */
		es = Executors.newSingleThreadExecutor();
		es.execute(task);
		if (es != null)
			es.shutdown();
		
		// shutDownNow() attempts to stop all running tasks and returns a List<Runnable> of 
		// tasks that were submitted to the thread executor but were never started.
	}
	
	public static void singleThreadExecutorWithSubmit() throws Exception {
		ExecutorService es = null;
		
		try {
			Runnable task = () -> Stream.iterate(1, n -> n < 1000, n -> n + 1).forEach(System.out::println);
			es = Executors.newSingleThreadExecutor();
			var res = es.submit(task); //Future object is returned here.
		
			/* boolean isDone(), boolean isCancelled(), boolean cancel(boolean myInterruptIfRunning), V get(), V get(long timeout, TimeUnit unit) */
			// Won't return anything since Runnable does not either.
			res.get(10, TimeUnit.SECONDS);
			
			print("Reached!");
		} catch (TimeoutException e) {
			print("Not reached in time!");
		} finally {
			if (es != null)
				es.shutdown();
		}
	}
	
	public static void singleThreadExecutorWithSubmitAndCallable() throws Exception {
		/* Callable returns a value and throws a checked exception. */
		ExecutorService es = null;
		
		try {
			es = Executors.newSingleThreadExecutor();
			var res = es.submit(() -> 48); // return 48
			print(res.get());
		} finally {
			if(es != null)
				es.shutdown();
		}
		
		/* awaitTermination(1, TimeUnit.MINUTES): Waits specified time to complete all tasks or throws an InterruptedException. 
		   Returns sooner if all tasks finish earlier.
		   if awaitTermination is called before shutdown() then the full timeout value will be used.
		   es.isTerminated() can confirm if the executor has been terminated or not. */
	}
	
	/* invokeAll() and invokeAny() both include an overloaded version that accepts a timeout value. */
	public static void singleThreadExecutorWithInvokeAll() throws InterruptedException, ExecutionException {
		ExecutorService es = null;
		
		try {
			es = Executors.newSingleThreadExecutor();
			
			/* All tasks are invoked one by one. All tasks are executed synchronously before moving to next line. */
			List<Future<String>> res = es.invokeAll(
				List.of(
					() -> "Task 1",
					() -> "Task 2",
					() -> "Task 3"
				)
			);
			
			for (var f : res){
				print(f.get());
			}
		} finally {
			if (es != null)
				es.shutdown();
		}
	}
	
	public static void singleThreadExecutorWithInvokeAny() throws InterruptedException, ExecutionException {
		ExecutorService es = null;
		
		try {
			es = Executors.newSingleThreadExecutor();
			
			/* Any task that completes first is returned but that may not always be the case. */
			String res = es.invokeAny(
				List.of(
					() -> "Task 1",
					() -> "Task 2",
					() -> "Task 3"
				)
			);
			
			print(res);
		} finally {
			if (es != null)
				es.shutdown();
		}
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}

class PrintData implements Runnable {
	@Override public void run(){
		Stream.of(1, 2, 3).forEach(System.out::println);
	}
}

class PrintThread extends Thread {
	@Override public void run(){
		Stream.of(1, 2, 3).forEach(System.out::println);
	}
}