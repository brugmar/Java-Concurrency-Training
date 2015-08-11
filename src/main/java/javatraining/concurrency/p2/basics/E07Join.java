
package javatraining.concurrency.p2.basics;

import java.util.concurrent.TimeUnit;


public class E07Join {
    
    private static class SleepingThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("SleepingThread: just started.");
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                System.out.println("SleepingThread: I'm quitting!!.");
            } catch (InterruptedException ex) {
            }
        }
    }
    
    public static void main(String... args) throws InterruptedException{
        Thread t = new Thread(new SleepingThread());
        t.start();
        System.out.println("Main thread: waiting for SleepingThread.");
        t.join();
        System.out.println("Main thread: SleepingThread has finished. Quitting.");
    }
    
}
