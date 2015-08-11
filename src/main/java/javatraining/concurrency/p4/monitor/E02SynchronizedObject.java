package javatraining.concurrency.p4.monitor;

public class E02SynchronizedObject {

    private static class WiredClass {

        private int value;
        private Object lockObject1 = new Object();
        private Object lockObject2 = new Object();

        public WiredClass(int initValue) {
            this.value = initValue;
        }

        public void dec() {
            synchronized (lockObject1) {
                value--;
            }
        }

        public void inc() {
            // synchronization on non-final field
            synchronized (lockObject2) {
                value++;
            }
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String... args) throws InterruptedException {
        final WiredClass wc = new WiredClass(0);
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    wc.inc();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
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
