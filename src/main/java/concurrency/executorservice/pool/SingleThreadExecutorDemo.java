package concurrency.executorservice.pool;

import java.util.concurrent.*;

public class SingleThreadExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            int id = i;
            ex.submit(() -> {
                System.out.println("Step " + id + " on " + Thread.currentThread().getName());

            });
        }

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Done in order.");
    }
}
