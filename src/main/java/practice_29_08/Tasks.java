package practice_29_08;

public class Tasks {

    // Задача 1:
    // Поочередная синхронизация. Два потока выводят сообщение на экран.

    /*
      Задача 1:
          Предположим что у вас есть класс Singleton, у которого есть приватный конструктор и статический метод:

          pubilc class Singleton {

            private Singleton() {
            }

            public static Singleton getOrCreate() {};
          }

          Напишите реализацию этого метода, который будет либо создавать объект Singleton, либо возвращать уже созданный. Ваше
          решение должно работать в многопоточном режиме. До первого вызова объект заранее создавать нельзя.

          Предложите простое решение и подумайте как его сделать эффективнее.
      Задача 2:
            Распределение задач для многопоточной обработки данных. Предположим у вас есть текстовый файл, который
            содержит записи в каждой строке. Ваш нужно написать класс, который выглядит так:

            public class FileProcessor {

                void process(File file, Processor processor) {};
            }

            interface Processor {
                void process(String line);
            }

            При этом реализация интерфейса Processor предполагает, что каждая строка может обрабатываться долго.
            Предложите решение для организации параллельной обработки строк файла в классе FileProcessor. Помните, что
            количество созданных Thread в реальных системах ограничено ресурсами комьютера. Как можно их ограничить?

            Напишите тесты для вашего решения.

      Задача 3:
          Есть два потока, которые печатают сообщение на экран (ниже). Требуется дополнить код таким образом, чтобы
          задержка между сообщениями между разными потоками была как минимум 1 секунда.

          new Thread(() -> {
           while (true) {
               System.out.println("Thread 1 msg");
           }
          }).start();

          new Thread(() -> {
           while (true) {
               System.out.println("Thread 2 msg");
           }
          }).start();
     */
}
