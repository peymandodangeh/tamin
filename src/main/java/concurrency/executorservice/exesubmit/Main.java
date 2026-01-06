package concurrency.executorservice.exesubmit;

import java.util.concurrent.*;

public class Main {
    static CompletableFuture<String> fetchDataAsync() {
        return CompletableFuture.supplyAsync(() -> {
            // شبیه‌سازی I/O (مثلاً شبکه/دیتابیس)
            try { Thread.sleep(1500); } catch (InterruptedException e) { throw new RuntimeException(e); }
            return "DATA-123";
        });
    }

    public static void main(String[] args) throws Exception {

        fetchDataAsync()
                .thenAccept(data -> {
                    // ✅ اینجا وقتی دیتا آماده شد، خودکار اجرا میشه
                    System.out.println("Callback got data: " + data);
                })
                .exceptionally(ex -> {
                    System.out.println("Error: " + ex);
                    return null;
                });

        // 👇 برنامه اینجا ادامه میده، منتظر get نمی‌مونه
        for (int i = 0; i < 5; i++) {
            System.out.println("Main doing other work... " + i);
            Thread.sleep(400);
        }

        // نکته: برای اینکه برنامه قبل از رسیدن دیتا terminate نشه،
        // اینجا فقط برای demo یه کم صبر کردیم. (در سرور واقعی معمولاً برنامه زنده است)
        Thread.sleep(2000);
    }
}
