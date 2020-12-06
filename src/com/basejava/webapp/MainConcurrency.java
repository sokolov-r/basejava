package com.basejava.webapp;

public class MainConcurrency {
    private static final Object OBJECT1 = new Object();
    private static final Object OBJECT2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (OBJECT1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (OBJECT2) {
                }
            }
        }).start();

        synchronized (OBJECT2) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (OBJECT1) {
            }
        }
    }
}
