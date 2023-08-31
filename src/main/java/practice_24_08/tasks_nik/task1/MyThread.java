package practice_24_08.tasks_nik.task1;

public class MyThread extends Thread {
    //исспользуем переменную класса для управления выходом из цикла
    //volatile чтоб гарантировать что поток сразу увидит изменения извне и остановит работу
    private volatile boolean isStopped;

    //дадим доступ к переменной через геттер потоку main
    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    @Override
    public void run() {
        // в цикле каждую секунду выводим сообщение пока поток извне не поменяет значение переменной на true
        while (!isStopped) {
            System.out.println("some text");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
