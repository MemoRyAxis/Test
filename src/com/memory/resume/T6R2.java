package com.memory.resume;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author memoryaxis@gmail.com
 * @TODO sub thread can't notice main thread
 */
public class T6R2 {

    public static void main(String[] args) {
        System.out.println("main thread: before logic...");
        Callable<Boolean> callable = () -> {
            System.out.println("sub thread: logic start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            System.out.println("sub thread: logic end");
            return true;
        };

        FutureTask<Boolean> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println("main thread: after logic...");
    }

}
