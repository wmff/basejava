package ru.javawebinar.basejava;

class MainDeadlock implements Runnable{
    private final static Object OBJECT_1 = new Object();
    private final static Object OBJECT_2 = new Object();

    private static int counter;

    public static void main(String args[]) {
        MainDeadlock mainDeadlock = new MainDeadlock();
        mainDeadlock.run();
    }

    @Override
    public void run() {
        final Thread THREAD_1 = new Thread(()->{
            synchronized (OBJECT_1) {
                for (int i = 0; i < 5_000; i++) {
                    for (int j = 0; j < 100; j++) {
                        this.inc();
                    }
                }
                System.out.println("lock " + getThread().getName());
                synchronized (OBJECT_2) {
                    System.out.println("run " + getThread().getName());
                }
            }
        });

        final Thread THREAD_2 = new Thread(()->{
            synchronized (OBJECT_2) {
                for (int i = 0; i < 5_000; i++) {
                    for (int j = 0; j < 100; j++) {
                        this.inc();
                    }
                }
                System.out.println("lock " + getThread().getName());
                synchronized (OBJECT_1) {
                    System.out.println("run " + getThread().getName());
                }
            }
        });

        THREAD_1.start();
        THREAD_2.start();

        try {
            THREAD_1.join();
            THREAD_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }

    private Thread getThread() {
        return Thread.currentThread();
    }

    private void inc() {
        synchronized (MainDeadlock.class) {
            counter++;
        }
    }
}