package com.memory.resume;

/**
 * @author memoryaxis@gmail.com
 */
public class T6 {

    public static void main(String[] args) {
        new MainThread().mainLogic();
    }
}

class MainThread {

    void mainLogic() {
        System.out.println("main thread: before logic...");
        new SubThread(this).start();
        System.out.println("main thread: after logic...");
    }

    void handleSubThreadMessage() {
        System.out.println("main thread: sub thread is done.");
    }
}

class SubThread extends Thread {

    private final MainThread thread;

    SubThread(MainThread thread) {
        this.thread = thread;
    }

    private void subThreadLogic() {
        System.out.println("sub thread: logic start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sub thread: logic end");
        thread.handleSubThreadMessage();
    }

    @Override
    public void run() {
        subThreadLogic();
    }

}
