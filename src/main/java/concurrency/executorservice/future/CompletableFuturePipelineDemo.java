package concurrency.executorservice.future;

import java.util.concurrent.*;

public class CompletableFuturePipelineDemo {
    public static void main(String[] args) {
        CompletableFuture
                .supplyAsync(() -> "  hello  ")
                .thenApply(String::trim)
                .thenApply(String::toUpperCase)
                .thenAccept(s -> System.out.println("Final: " + s))
                .join(); // فقط برای اینکه demo تمام شود
    }
}
