package javatraining.concurrency.p2.basics;

import java.util.Random;

public class E05InterruptAndStop {

    private static class LongExecution implements Runnable {

        @Override
        public void run() {
            try {
                Random r = new Random();
                long upper = 1L * Integer.MAX_VALUE + r.nextInt();
                long out = 1;
                for (int i = 0; i < upper; i++) {
                    
                    // isInterrupted doesn't clean the flag
                    if (Thread.currentThread().isInterrupted()){
                        throw new InterruptedException();
                    }
                    
                    if (i % 2 == 0) {
                        out += 1;
                    } else {
                        out += 3;
                    }
                }
                System.out.println(out);
            } catch (InterruptedException ex){
                System.out.println("Thread was interrupted.");
            }
        }
    }

    public static void main(String... args) {
        Thread t = new Thread(new LongExecution());
        t.start();
        t.interrupt();
    }
}
