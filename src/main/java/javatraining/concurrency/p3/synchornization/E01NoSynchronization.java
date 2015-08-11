
package javatraining.concurrency.p3.synchornization;

public class E01NoSynchronization {

    public static int counter = 0;
    
    private static class IncrementRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        }
        
    }
    
    public static void main(String... args) throws InterruptedException{
        Thread t1 = new Thread(new IncrementRunnable());
        Thread t2 = new Thread(new IncrementRunnable());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
    }
}
