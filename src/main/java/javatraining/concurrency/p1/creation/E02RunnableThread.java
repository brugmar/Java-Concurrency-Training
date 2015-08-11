package javatraining.concurrency.p1.creation;

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("My thread.");
    }
}

public class E02RunnableThread {
    public static void main(String... args){
        Thread t = new Thread(new MyRunnable());
        t.start();
        System.out.println("Main is done.");
    }
}
