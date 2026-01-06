package concurrency.executorservice;

import java.util.concurrent.*;

public class Demo1Executor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

//        Future<?> f  = executor.submit(()-> {
//                    System.out.println("hello");
//                    throw new RuntimeException("this is dummy exception");
//                }
//
//        );
//        System.out.println("dummy");
//        try {
//            System.out.println(f.get());
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        executor.submit( () -> {
//
//            try {
//                Thread.currentThread().sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("this is a runnable object");
//                }
//        );
//

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println(
                        "Task " + taskId +
                                " running on " + Thread.currentThread().getName()
                );
            });
        }

        System.out.println("before shutdown");
        if(!executor.awaitTermination(1 , TimeUnit.MILLISECONDS)){
            executor.shutdownNow();
        }
        executor.shutdown(); // بسیار مهم
        System.out.println("after shutdown");
//        کار جدید نپذیر، ولی کارهای قبلی را تمام کن
    }
}
