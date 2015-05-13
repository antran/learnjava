package com.antt.jcore.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lavalamp on 5/13/15.
 */

// Thread A & B both run a shareRunnable object that use a threadLocal var
// shareRunnable call threadLocal.set(Random())
// thread A & B will see different value if call threadLocal.get();
// although they are share the same code.
// All threads use the same threadLocal instance but the value is different.

class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);
    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            System.out.println("\tIs called by each thread. Initialize ThreadID. Thread " + Thread.currentThread().getName());
            return nextId.getAndIncrement();
        }
    };

    public static int getId() {
        System.out.println("\tThread id " + threadId);
        return threadId.get();
    }
}

public class ThreadLocalExp {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Running " + Thread.currentThread().getName());
                ThreadId tid = new ThreadId();
                for (int i = 0; i < 2; i++) {
                    System.out.printf("Thread %s, thread id %d\n", Thread.currentThread().getName(), tid.getId());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Running " + Thread.currentThread().getName());
                ThreadId tid = new ThreadId();
                for (int i = 0; i < 2; i++) {
                    System.out.printf("Thread %s, thread id %d\n", Thread.currentThread().getName(), tid.getId());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        Thread.currentThread().join();
    }
}
