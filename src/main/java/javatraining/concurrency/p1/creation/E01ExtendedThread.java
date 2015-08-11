
package javatraining.concurrency.p1.creation;

class FirstThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread is done.");
    }
}

public class E01ExtendedThread {
    public static void main(String... args){
        FirstThread ft = new FirstThread();
        ft.start();
        System.out.println("Main is done.");
    }
}


