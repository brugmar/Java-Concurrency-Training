package javatraining.concurrency.p6.executors;

import java.util.Random;
import java.util.concurrent.Executor;

class SimpleExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
    
}

public class E01SimpleExecutor {
    public static void main(String... args){
        Runnable randomNumberPrinter = new Runnable(){

            @Override
            public void run() {
                System.out.println(new Random().nextInt());
            }
        };
        
        SimpleExecutor executor = new SimpleExecutor();
        
        executor.execute(randomNumberPrinter);
        executor.execute(randomNumberPrinter);
    }
}
