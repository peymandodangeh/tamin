package concurrency.executorservice.exesubmit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf =
                CompletableFuture.supplyAsync(() -> {
                                    try {
                                        Thread.sleep(5000);
                                        return "hello";
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        )
                        .thenApply(String::trim)
                        .thenApply(String::toUpperCase);

        System.out.println(cf.get()); // "HELLO"
        System.out.println("Done");
    }

}
