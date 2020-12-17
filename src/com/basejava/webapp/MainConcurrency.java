package com.basejava.webapp;

public class MainConcurrency {
    private static final Object OBJECT1 = new Object();
    private static final Object OBJECT2 = new Object();

    public static void main(String[] args) {
        lock(OBJECT1, OBJECT2);
        lock(OBJECT2, OBJECT1);
    }

    public static void lock(Object object1, Object object2) {
        new Thread(() -> {
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + " holding " + object1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting " + object2);
                synchronized (object2) {
                    System.out.println("This line should not be printed if deadlock happens");
                }
            }
        }).start();
    }
}
