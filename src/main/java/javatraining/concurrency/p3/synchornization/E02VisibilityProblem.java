/*
 * Example for Java Concurrency in Practice book
*/

package javatraining.concurrency.p3.synchornization;

class Holder {

    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("This statement is false.");
        }
    }
}

public class E02VisibilityProblem {
    
    public static void test() throws InterruptedException{
        final Holder h = new Holder(3);
        Thread t1 = new Thread(new Runnable() {
            
            @Override
            public void run() {
                h.assertSanity();
            }
        });
        t1.start();
        t1.join();
    }

    public static void main(String... args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            test();
        }
    }
}
