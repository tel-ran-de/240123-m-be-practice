package practice_22_08.counting_threads;

public class CountingThread extends Thread {

    private final int start;
    private int sum;

    public CountingThread(int start) {
        this.start = start;
    }

    @Override
    public void run() {
        for (int i = start; i < start + 100; i++) {
            sum += i;
        }
    }

    public int getSum() {
        return sum;
    }
}
