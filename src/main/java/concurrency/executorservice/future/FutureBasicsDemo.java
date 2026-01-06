package concurrency.executorservice.future;

import java.util.concurrent.*;

public class FutureBasicsDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService ex = Executors.newFixedThreadPool(2);

        Future<Integer> f = ex.submit(() -> {
            Thread.sleep(800); // شبیه‌سازی I/O
            return 42;
        });

        try {
            // اگر دیرتر از 300ms شد، timeout
            Integer result = f.get(300, TimeUnit.MILLISECONDS);
            System.out.println("Result: " + result);
        } catch (TimeoutException te) {
            System.out.println("Timeout -> cancel(true)");
            f.cancel(true); // تلاش برای interrupt
        } catch (ExecutionException ee) {
            System.out.println("Failed: " + ee.getCause());
        } finally {
            ex.shutdown();
            ex.awaitTermination(2, TimeUnit.SECONDS);
        }

        System.out.println("Done. isCancelled=" + f.isCancelled() + ", isDone=" + f.isDone());
    }
}
