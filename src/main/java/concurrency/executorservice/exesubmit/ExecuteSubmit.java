package concurrency.executorservice.exesubmit;

import java.util.concurrent.*;

public class ExecuteSubmit {

    //execute fire-and-forget

    public static void main(String[] args) throws InterruptedException {

        ExecutorService ex = Executors.newFixedThreadPool(1);

        ex.execute(() -> {
            throw new RuntimeException("Boom");
        });

        ex.shutdown();


        ExecutorService executor = Executors.newFixedThreadPool(2);

//        executor.execute(() -> {
//            System.out.println("Task started");
//            if (true) {
//                throw new RuntimeException("Boom!");
//            }
//        });

        Future<?> f = executor.submit(() -> System.out.println("hi"));
        try {
            System.out.printf("%s%n", f.get()); // فقط منتظر اتمام کار می‌ماند
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
//
//        Future<String> f = executor.submit(() -> doWork(), "OK");
//        System.out.println(f.get()); // "OK"
//
//        Future<Integer> f = executor.submit(() -> 42);
//        System.out.println(f.get()); // 42



        System.out.println("end");
        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow(); // تلاش برای interrupt
        }
    }
}
