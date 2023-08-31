package practice_29_08.hometask_nik;

//         Есть два потока, которые печатают сообщение на экран (ниже). Требуется дополнить код таким образом, чтобы
//         задержка между сообщениями между разными потоками была как минимум 1 секунда.

public class Task3_2 {

    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        System.out.println("Thread 1 msg");
                        lock.notify();
                        Thread.sleep(1000);
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        System.out.println("Thread 2 msg");
                        lock.notify();
                        Thread.sleep(1000);
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();


    }
}
