package pkg;

import java.util.concurrent.*;

class Scheduler {
	public static void main(String[] args) throws Exception {
		// scheduleWithRunnable();
		
		// scheduleWithCallable();
		
		scheduleAtFixedRate();
	}
	
	public static void scheduleWithRunnable() throws InterruptedException {
		ScheduledExecutorService ses = null;
		
		try {
			ses = Executors.newSingleThreadScheduledExecutor();
			/* Execute this 10 seconds into the future. */
			ScheduledFuture<?> f = ses.schedule(()-> print("I was scheduled!"), 10, TimeUnit.SECONDS);
			print(f.getDelay(TimeUnit.SECONDS));
		} finally {
			if (ses != null && !ses.isShutdown()){
				ses.shutdown();
			}
		}
	}
	
	public static void scheduleWithCallable() throws InterruptedException, ExecutionException, TimeoutException {
		ScheduledExecutorService ses = null;
		
		try {
			ses = Executors.newSingleThreadScheduledExecutor();
			/* Execute this 10 seconds into the future. */
			ScheduledFuture<?> f = ses.schedule(() -> "I was scheduled!", 10, TimeUnit.SECONDS);
			// ExecutionException thrown by get method.
			print(f.get(20, TimeUnit.SECONDS));
		} finally {
			if (ses != null && !ses.isShutdown()){
				ses.shutdown();
			}
		}
	}
	
	/* scheduleWithFixedDelay only starts the delay count once the task at hand is finished. */
	public static void scheduleAtFixedRate() {
		ScheduledExecutorService ses = null;
		
		try {
			ses = Executors.newSingleThreadScheduledExecutor();
			/* Execute this every 10 seconds, after an initial 10 second delay. */
			ScheduledFuture<?> f = ses.scheduleAtFixedRate(() -> print("I run every 10 seconds!"), 10, 10, TimeUnit.SECONDS);
			// ExecutionException thrown by get method.
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}