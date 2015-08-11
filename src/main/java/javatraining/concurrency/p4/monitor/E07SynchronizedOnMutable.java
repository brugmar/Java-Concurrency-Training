package javatraining.concurrency.p4.monitor;

class MutableInteger {
    private int value;

    public MutableInteger(int value) {
        this.value = value;
    }
    
    public void inc(){
        value++;
    }
    
    public void dec(){
        value--;
    }

    public int getValue() {
        return value;
    }
}

public class E07SynchronizedOnMutable {

    private static class WiredClass {

        // Good practise: acquire lock only on final fields!
        private volatile MutableInteger value;

        public WiredClass(int initValue) {
            this.value = new MutableInteger(initValue);
        }

        public void dec() {
            synchronized (value) {
                value.dec();
            }
        }

        public void inc() {
            synchronized (value) {
                value.inc();
            }
        }

        public int getValue() {
            return value.getValue();
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
