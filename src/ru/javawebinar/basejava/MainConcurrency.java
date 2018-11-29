package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static final int THREADS_NUMBER = 10_000;
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " " + getState());
            }
        };
//        thread0.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
            }
        });
//        thread1.start();

        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState()));
//        thread2.start();

//        System.out.println(thread0.getName() + " " + thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
//            System.out.println(thread.getState());
//            thread.start();
//            System.out.println(thread.getState());
//            thread.join();
//            System.out.println(thread.getState());
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(mainConcurrency.counter);
//        LazySingleton.getInstance();

    }

    private synchronized void inc() {
//        synchronized (this) {
            counter++;
//        }
    }
}