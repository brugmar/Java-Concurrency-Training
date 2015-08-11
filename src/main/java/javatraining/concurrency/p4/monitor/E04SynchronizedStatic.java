package javatraining.concurrency.p4.monitor;

public class E04SynchronizedStatic {

    private static class WiredClass {

        private static volatile int value = 0;

        public synchronized static void dec() {
                value--;
        }

        public synchronized void inc() {
                value++;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String... args) throws InterruptedException {
        final WiredClass wc = new WiredClass();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    wc.inc();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    wc.dec();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(wc.getValue());
    }
}
