package javatraining.concurrency.p6.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class LongSqr implements Callable<Long> {

    private final int value;

    public LongSqr(int value) {
        this.value = value;
    }

    @Override
    public Long call() throws Exception {
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        return 1L * value * value;
    }

}

public class E02ExecutorService {

    public static void main(String... args) throws InterruptedException, ExecutionException {
        LongSqr ls1 = new LongSqr(2);
        LongSqr ls2 = new LongSqr(3);
        LongSqr ls3 = new LongSqr(4);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            Future<Long> r1 = executor.submit(ls1);
            Future<Long> r2 = executor.submit(ls2);
            Future<Long> r3 = executor.submit(ls3);

            System.out.println(r1.get());
            System.out.println(r2.get());
            System.out.println(r3.get());

//        System.out.println(r3.get());
//        System.out.println(r1.get());
//        System.out.println(r2.get());
        } finally {
            executor.shutdown();
        }
    }
}
