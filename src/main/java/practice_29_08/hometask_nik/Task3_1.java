package practice_29_08.hometask_nik;

import java.util.concurrent.locks.ReentrantLock;

public class Task3_1 {

    public static void main(String[] args) {
//         Есть два потока, которые печатают сообщение на экран (ниже). Требуется дополнить код таким образом, чтобы
//         задержка между сообщениями между разными потоками была как минимум 1 секунда.

        ReentrantLock lock = new ReentrantLock(true);

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    Thread.sleep(1000);
                    System.out.println("Thread 1 msg");
                    lock.unlock();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    Thread.sleep(1000);
                    System.out.println("Thread 2 msg");
                    lock.unlock();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
