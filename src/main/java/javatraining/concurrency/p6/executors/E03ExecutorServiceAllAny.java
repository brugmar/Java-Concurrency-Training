package javatraining.concurrency.p6.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class E03ExecutorServiceAllAny {

    public static void main(String... args) throws InterruptedException, ExecutionException {
        LongSqr ls1 = new LongSqr(2);
        LongSqr ls2 = new LongSqr(3);
        LongSqr ls3 = new LongSqr(4);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            List<Future<Long>> allResults = executor.invokeAll(Arrays.asList(ls1, ls2, ls3));

            for (Future<Long> result : allResults) {
                System.out.println(result.get());
            }

            System.out.println("Invoke any:");

            Long anySolution = executor.invokeAny(Arrays.asList(ls1, ls2, ls3));
            System.out.println(anySolution);
        } finally {
            executor.shutdown();
        }
    }
}
