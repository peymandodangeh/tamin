package concurrency.executorservice.exesubmit;

import java.util.concurrent.*;

public class DemoCompletionService {
    public static void main(String[] args) throws Exception {
        ExecutorService ex = Executors.newFixedThreadPool(4);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(ex);

        for (int i = 0; i < 10; i++) {
            int id = i;
            cs.submit(() -> {
                Thread.sleep((long)(Math.random() * 500));
                return id;
            });
        }

        for (int i = 0; i < 10; i++) {
            Future<Integer> f = cs.take(); // هر کدام زودتر آماده شد
            System.out.println("Done: " + f.get());
        }

        ex.shutdown();
    }
}

