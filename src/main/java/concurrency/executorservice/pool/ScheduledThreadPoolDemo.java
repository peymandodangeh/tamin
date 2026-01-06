package concurrency.executorservice.pool;

import java.util.concurrent.*;

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService ex = Executors.newScheduledThreadPool(2);

        ex.scheduleAtFixedRate(() -> {
            System.out.println("Heartbeat: " + System.currentTimeMillis() +
                    " on " + Thread.currentThread().getName());
        }, 0, 1, TimeUnit.SECONDS);

        // برنامه را 5 ثانیه زنده نگه می‌داریم
        Thread.sleep(5000);

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Stopped.");
    }
}
