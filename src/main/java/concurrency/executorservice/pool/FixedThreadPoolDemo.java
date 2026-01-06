package concurrency.executorservice.pool;

import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;


public class FixedThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        int n = Runtime.getRuntime().availableProcessors();
        ExecutorService ex = Executors.newFixedThreadPool(n);

        for (int i = 0; i < 10; i++) {
            int id = i;
            ex.submit(() -> {
                System.out.println("Task " + id + " on " + Thread.currentThread().getName());
                // شبیه‌سازی کار CPU-bound
                long sum = 0;
                for (int k = 0; k < 50_000_000; k++) sum += k;
                return sum;
            });
        }

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Done.");
    }
}
