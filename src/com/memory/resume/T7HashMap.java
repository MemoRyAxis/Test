package com.memory.resume;

/**
 * @author memoryaxis@gmail.com
 */
public class T7HashMap {

    public static void main(String[] args) {
        int a = 1000;
        int b = 101;
        int c = 1;

        int r;

        System.out.println(16 & 16);

        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(~a));
        System.out.println(Integer.toBinaryString(~b));

        r = a ^ b;
        System.out.println(r);
        r = b ^ a;
        System.out.println(r);

        r = a | b;
        System.out.println(r);
        r = b | a;
        System.out.println(r);

        r = a & c;
        System.out.println(r);
        r = b & c;
        System.out.println(r);
        r = a & b;
        System.out.println(r);


    }
}
