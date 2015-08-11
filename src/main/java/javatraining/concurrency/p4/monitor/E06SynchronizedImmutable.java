package javatraining.concurrency.p4.monitor;

public class E06SynchronizedImmutable {

    private static class WiredClass {

        private volatile Integer value;

        public WiredClass(int initValue) {
            this.value = initValue;
        }

        public void dec() {
            synchronized (value) {
                value--;
            }
        }

        public void inc() {
            synchronized (value) {
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
