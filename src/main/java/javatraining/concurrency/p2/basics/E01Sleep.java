
package javatraining.concurrency.p2.basics;

import java.util.concurrent.TimeUnit;

public class E01Sleep {
    public static void main(String... args) throws InterruptedException{
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    System.out.println("Child thread: going to sleep for 1 sec.");
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                    System.out.println("Child thread: I'm awaken. Time to quit.");
                } catch (InterruptedException ex) {
                }
            }
            
        }).start();
        
        System.out.println("Main thread: Going to sleep for 2 seconds.");
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        System.out.println("Main thread: I'm awaken. Time to quit.");
    }
}
