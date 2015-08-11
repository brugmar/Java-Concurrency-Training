package javatraining.concurrency.p2.basics;

import java.util.Random;

public class E06InterruptAndStop {

    private static class LongExecution implements Runnable {

        @Override
        public void run() {
            Random r = new Random();
            long upper = 1L * Integer.MAX_VALUE + r.nextInt();
            long out = 1;
            for (int i = 0; i < upper; i++) {
                if (i % 2 == 0) {
                    out += 1;
                } else {
                    out += 3;
                }
            }
            System.out.println(out);
        }
    }

    public static void main(String... args) {
        Thread t = new Thread(new LongExecution());
        
        // Throws ThreadDeath error
        t.stop();
    }
}
