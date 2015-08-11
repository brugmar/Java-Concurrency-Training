package javatraining.concurrency.p6.executors;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Bookshelf {

    private int pages = 800;
    private String title = "Czarodziejska gora";

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String title, int pages) {
        lock.writeLock().lock();
        try {
            System.out.format("New title (%s, %d).\n", title, pages);
            this.title = title;
            this.pages = pages;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String getBookInfo() {
        lock.readLock().lock();
        try {
            return title + ": " + pages + " pages";
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class E05ReentrantReadWriteLock {

    public static void main(String... args) {
        final Bookshelf shelf = new Bookshelf();
        Runnable Reader = new Runnable() {

            @Override
            public void run() {
                System.out.format("%s get book %s\n", Thread.currentThread().getName(), shelf.getBookInfo());
            }
        };

        Runnable Writer = new Runnable() {

            @Override
            public void run() {
                Random r = new Random();
                String bookTitile = "Book" + r.nextInt(50);
                int pages = r.nextInt(1000);
                shelf.put(bookTitile, pages);
            }
        };

        for (int i = 0; i < 100; i++) {
            new Thread(Reader, "Reader" + i).start();
            if (i % 3 == 0) {
                new Thread(Writer, "Writer" + i).start();
            }
        }
    }
}
