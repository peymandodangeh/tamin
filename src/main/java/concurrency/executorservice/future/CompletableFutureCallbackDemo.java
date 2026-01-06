package concurrency.executorservice.future;

import java.util.concurrent.*;

public class CompletableFutureCallbackDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService ex = Executors.newFixedThreadPool(2);

        CompletableFuture<String> cf =
                CompletableFuture.supplyAsync(() -> {
                    sleep(700);
                    return "DATA-123";
                }, ex);

        // ✅ بدون get: وقتی دیتا آماده شد، callback اجرا می‌شود
        cf.thenAccept(data -> System.out.println("Callback received: " + data))
                .exceptionally(ex2 -> { System.out.println("Error: " + ex2); return null; });

        // main کار خودش را ادامه می‌دهد
        for (int i = 0; i < 5; i++) {
            System.out.println("Main doing other work: " + i);
            Thread.sleep(150);
        }

        // فقط برای demo که برنامه قبل از callback تمام نشود
        Thread.sleep(1000);

        ex.shutdown();
        ex.awaitTermination(2, TimeUnit.SECONDS);
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
