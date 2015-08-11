package javatraining.concurrency.p4.monitor;

import java.util.Objects;
import java.util.Random;

class SingletonBuffer {

    private volatile Integer value;

    public synchronized void put(int value) {
        this.value = value;
        System.out.format("%s: putting new value %d.\n", Thread.currentThread().getName(), value);
        notifyAll();
    }

    public synchronized int get(Integer lastValue) throws InterruptedException {
        while (value == null || Objects.equals(lastValue, value)) {
            System.out.format("%s: waits for value.\n", Thread.currentThread().getName());
            wait();
        }
        System.out.format("%s: got value %d.\n", Thread.currentThread().getName(), value);
        return value;
    }
}

public class E08WaitNotify {

    public static void main(String... args) {
        final SingletonBuffer buff = new SingletonBuffer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    buff.get(null);
                } catch (InterruptedException ex) {
                }
            }
        }, "Reader").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                buff.put(new Random().nextInt());
            }
        }, "Writer").start();
    }
}
