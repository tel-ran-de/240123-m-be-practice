package practice_24_08.tasks_nik.task3;

import java.util.concurrent.CountDownLatch;

public class Task3 {
    // Рассмотрим такой код:
    // Thread t = new Thread(() -> {
    //     System.out.println("Another thread");
    // });
    // t.start();
    // System.out.println("Main thread");
    // t.join();
    // Как можно его изменить таким образом, чтобы гарантированно выводить сообщение на печать из потока до того,
    // как будет напечатано сообщение из Main треда?

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);

         Thread t = new Thread(() -> {
             System.out.println("Another thread");
             countDownLatch.countDown();
         });
         t.start();

        try {
            countDownLatch.await();

            System.out.println("Main thread");
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
