package practice_29_08.hometask_nik.task2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//Задача 2:
//        Распределение задач для многопоточной обработки данных. Предположим у вас есть текстовый файл, который
//        содержит записи в каждой строке. Ваш нужно написать класс, который выглядит так:
//
//public class FileProcessor {
//
//    void process(File file, Processor processor) {};
//
//    // ожидает выполнения всех задач, которые были созданы в #process(File, Processor);
//    void awaitAllTasks() throws InterruptedException;
//}
//
//interface Processor {
//    void process(String line);
//}
//
//    При этом реализация интерфейса Processor предполагает, что каждая строка может обрабатываться долго.
//        Предложите решение для организации параллельной обработки строк файла в классе FileProcessor. Помните, что
//        количество созданных Thread в реальных системах ограничено ресурсами комьютера. Как можно их ограничить?
//
//        Напишите тесты для вашего решения.
public class FileProcessor {
    private final ExecutorService executor;

    public FileProcessor(int numberOfThreads) {
        executor = Executors.newFixedThreadPool(numberOfThreads, new SimpleThreadFactory());
    }

    void process(File file, Processor processor) {

        if (file == null || processor == null) {
            System.out.println("check arguments");
            return;
        }

        List<Future<?>> futures = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while (true) {
                String line = bufferedReader.readLine();

                if (line == null) {
                    break;
                }
//                executor.execute(()-> processor.process(line));
                Future<?> task = executor.submit(() -> processor.process(line));
                futures.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(file);
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    };
    public void closeExecutor() {
        executor.shutdown();
    }
}

class SimpleThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread thread =  new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}