package practice_24_08.tasks_nik.task1;

public class Task1 {
    //  Задача 1:
    // Требуется создать поток, который будет писать сообщение на экран раз в секунду.
    // Поток Main должен стартануть этот поток, подождать 10 секунд, остановить созданный поток и подождать пока
    // он завершится.

    public static void main(String[] args) {
        //создаем и запускаем второй поток
        MyThread thrd = new MyThread();
        thrd.start();

        try {

            Thread.sleep(10000);
            //через 10 секунд из потока main завершаем второй поток
            thrd.setStopped(true);
            //дожидаемся его завершения (чтоб убедится что поток действительно завершен)
            thrd.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //успех
        System.out.println("main terminated");

    }
}
