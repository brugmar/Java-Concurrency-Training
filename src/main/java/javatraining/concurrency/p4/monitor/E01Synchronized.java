
package javatraining.concurrency.p4.monitor;

class CounterHolder {
    private volatile int counter = 0;

    public synchronized int inc() {
        return counter++;
    }

    public int getCounter() {
        return counter;
    }
}

public class E01Synchronized {

    private static class IncrementRunnable implements Runnable {
        
        CounterHolder counterHolder;

        public IncrementRunnable(CounterHolder counterHolder) {
            this.counterHolder = counterHolder;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counterHolder.inc();
            }
        }
        
    }
    
    public static void main(String... args) throws InterruptedException{
        CounterHolder counterHolder = new CounterHolder();
        Thread t1 = new Thread(new IncrementRunnable(counterHolder));
        Thread t2 = new Thread(new IncrementRunnable(counterHolder));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counterHolder.getCounter());
    }
}
