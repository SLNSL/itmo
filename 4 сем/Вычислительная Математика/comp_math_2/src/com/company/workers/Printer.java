package com.company.workers;

import java.io.FileWriter;
import java.io.IOException;

public class Printer {

    public static boolean isFile;
    private static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter("C:\\Users\\Никита\\Desktop\\Уник\\4 сем\\выч мат\\2my2\\output.out", false);
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
}
