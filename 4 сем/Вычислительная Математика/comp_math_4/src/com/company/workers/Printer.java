package com.company.workers;

import java.io.FileWriter;
import java.io.IOException;

public class Printer {

    public static boolean isFile;
    private static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter("C:\\Users\\Никита\\Desktop\\Уник\\4 сем\\выч мат\\4lab\\output.out", false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void println(String s) {

        try {
            if (!isFile) {

                System.out.println(s);
            } else {
                fileWriter.write(s + "\n");
                fileWriter.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void printf(String format, Object... args) {
        try {
            if (!isFile) {
                System.out.printf(format, args);
            } else {
                String s = String.format(format, args);
                fileWriter.write(s);
                fileWriter.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void print(String s) {

        try {
            if (!isFile) {

                System.out.print(s);
            } else {
                fileWriter.write(s);
                fileWriter.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void printErr(String s){
        try {
            if (!isFile) {

                System.err.print(s + "\n");
            } else {
                fileWriter.write(s);
                fileWriter.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
