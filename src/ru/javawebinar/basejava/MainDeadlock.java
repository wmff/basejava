package ru.javawebinar.basejava;

class MainDeadlock implements Runnable {
    private final static Object OBJECT_1 = new Object();
    private final static Object OBJECT_2 = new Object();

    public static void main(String args[]) {
        MainDeadlock mainDeadlock = new MainDeadlock();
        mainDeadlock.run();
    }

    @Override
    public void run() {
        final Thread THREAD_1 = new Thread(() -> someMethod(OBJECT_1, OBJECT_2));

        final Thread THREAD_2 = new Thread(() -> someMethod(OBJECT_2, OBJECT_1));

        THREAD_1.start();
        THREAD_2.start();

        try {
            THREAD_1.join();
            THREAD_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void someMethod(Object object1, Object object2) {
        synchronized (object1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock " + getThread().getName());
            synchronized (object2) {
                System.out.println("run " + getThread().getName());
            }
        }
    }

    private Thread getThread() {
        return Thread.currentThread();
    }

}