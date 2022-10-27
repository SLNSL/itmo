package com.company;

public class Printer {

    public static void print(Object o){
        System.out.print(o);
    }

    public static void println(Object o){
        System.out.println(o);
    }

    public static void printf(String format, Object... args){
        System.out.printf(format, args);
    }

    public static void printErr(Object o){
        System.err.println(o);
    }
}
