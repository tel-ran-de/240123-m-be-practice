package practice_24_08.tasks_nik.task2;

import java.util.concurrent.atomic.AtomicReference;

class MyCounter2 {

    private volatile AtomicReference<State> twoCounters = new AtomicReference<>(new State());

    public void increment() {

        twoCounters.getAndUpdate((state) -> {
            State state1 = new State();
            state1.setCounter(state.getCounter() + 1);
            state1.setOperationCounter(state.getOperationCounter() + 1);
            return state1;
        });
    }

    public void decrement() {

        twoCounters.getAndUpdate(state -> {
            State stateNew = new State();
            stateNew.setCounter(state.getCounter() - 1);
            stateNew.setOperationCounter(state.getOperationCounter() + 1);
            return stateNew;
        });
    }

    public int getOperationsCount() {
        return twoCounters.get().getCounter();
    }

    public int getCounterValue() {
        return twoCounters.get().getOperationCounter();
    }
}

class State {
    private int counter;
    private int operationCounter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getOperationCounter() {
        return operationCounter;
    }

    public void setOperationCounter(int operationCounter) {
        this.operationCounter = operationCounter;
    }
}