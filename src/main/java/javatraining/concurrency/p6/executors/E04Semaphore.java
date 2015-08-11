
package javatraining.concurrency.p6.executors;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class SecretRoom {
    private final Semaphore semaphore = new Semaphore(2);
    
    public void enterRoom() throws InterruptedException {
        semaphore.acquire();
        System.out.format("%s has just entered the secret room.\n", Thread.currentThread().getName());
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        System.out.format("%s is about to leave the secret room.\n", Thread.currentThread().getName());
        semaphore.release();
    }
}

public class E04Semaphore {
    public static void main(String... args){
        final SecretRoom room = new SecretRoom();
        Runnable entrant = new Runnable() {

            @Override
            public void run() {
                try {
                    room.enterRoom();
                } catch (InterruptedException ex) {
                }
            }
        };
        
        new Thread(entrant, "Person1").start();
        new Thread(entrant, "Person2").start();
        new Thread(entrant, "Person3").start();
    }
}
