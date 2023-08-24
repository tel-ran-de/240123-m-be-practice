package practice_22_08.counting_threads;

public class Main {

    // Нужно сделать три потока. Каждый из которых считает значения:
    // 1 поток: от 1 до 100
    // 2 поток: от 101 до 200
    // 3 поток: от 201 до 300
    // В main методе нужно дождаться выполнения этих вычислений и вывести сумму их на экран.
    public static void main(String[] args) throws InterruptedException {
        CountingThread t1 = new CountingThread(1);
        CountingThread t2 = new CountingThread(101);
        CountingThread t3 = new CountingThread(201);
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Sum = " + (t1.getSum() + t2.getSum() + t3.getSum()));
    }
}
