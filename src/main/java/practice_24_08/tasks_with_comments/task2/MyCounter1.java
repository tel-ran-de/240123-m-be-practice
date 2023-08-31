package practice_24_08.tasks_with_comments.task2;
// Задача 2:
// Требуется создать потокобезопасный класс, который отвечает за счётчик с суммой. Предложите две реализации.

// Дополнение: должно быть 2 счетчика. 1 - счетчик суммы. 2 - счетчик общего количества обращений к методам
// Также добавить 4й метод getSumCount

// Методы: increment()/decrement()/getOperationsCount()
class MyCounter1 {
    private int sumCounter;
    private int operationCounter;

    //делаем все методы класса synchronized тем самым разрешаем только 1 потоку
    //одновременно иметь доступ к любому из методов
    public synchronized void increment() {
        sumCounter++;
        operationCounter++;
    }

    public synchronized void decrement() {
        sumCounter--;
        operationCounter++;
    }

    public synchronized int getOperationsCount() {
        return operationCounter;
    }

    public synchronized int getSumCount() {
        return sumCounter;
    }
}





