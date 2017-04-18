package com.memory.resume;

/**
 * @author memoryaxis@gmail.com
 */
public class T4 {

    public static void main(String[] args) {
//        t4OutOfMemory();
//        t4StackOverFlow();
    }

    public static void t4OutOfMemory() {
        char[] carr = new char[Integer.MAX_VALUE];
    }

    public static void t4StackOverFlow() {
        t4StackOverFlow();
    }

}
