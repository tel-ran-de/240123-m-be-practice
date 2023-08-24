package practice_22_08;

import java.util.ArrayList;
import java.util.List;

public class Tasks {

    public static void main(String[] args) throws InterruptedException {
        // Нужно сделать три потока. Каждый из которых считает значения:
        // 1 поток: от 1 до 100
        // 2 поток: от 101 до 200
        // 3 поток: от 201 до 300
        // В main методе нужно дождаться выполнения этих вычислений и вывести сумму их на экран.
        List<String> list = new ArrayList<>();
        list.add("foo");

        BooleanTest r = new BooleanTest(list);
        Thread t1 = new Thread(r);
        t1.start();

        while (true) {
            System.out.println(list.get(0));
            Thread.sleep(1000);
        }
    }
}

class BooleanTest implements Runnable {

    volatile List<String> list;

    public BooleanTest(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
//        list.set(0, "baz");
//        list = List.of("a", "b", "c");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class MyRunnable implements Runnable {
    private final List<String> list;

    public MyRunnable(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        list.add("bar");
        System.out.println("Hello from" + Thread.currentThread().getName());
    }
}