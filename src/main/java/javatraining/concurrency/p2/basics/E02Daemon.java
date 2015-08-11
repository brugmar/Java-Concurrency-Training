
package javatraining.concurrency.p2.basics;

import java.util.concurrent.TimeUnit;

class DaemonRutine implements Runnable {

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("Daemon is still working...");
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException ex) {
            }
        }
    }
}

public class E02Daemon {
    public static void main(String... args){
        Thread thread = new Thread(new DaemonRutine());
        thread.setDaemon(false);
        thread.start();
    }
}
