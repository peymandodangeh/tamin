package concurrency.executorservice.pool;

import java.util.concurrent.*;

public class CachedThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newCachedThreadPool();

        for (int i = 0; i < 50; i++) {
            int id = i;
            ex.submit(() -> {
                System.out.println("IO Task " + id + " on " + Thread.currentThread().getName());
                // شبیه‌سازی I/O: خواب یعنی انتظار
                Thread.sleep(200);
                return null;
            });
        }

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Done.");
    }
}
