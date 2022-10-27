package com.company.tables;

import com.company.Printer;

public class SimpsonTable {

    public static String header;
    public static String xs;
    public static String ys;

    public static String headerPrev;
    public static String xsPrev;
    public static String ysPrev;

    public static boolean isLarge = false;

    public static int counterForY = 0;


    public static void makeSimpsonHeader(double n){
        if (n > 100) isLarge = true;
        if (isLarge) n = 100;
        counterForY = 0;

        headerPrev = header;
        xsPrev = xs;
        ysPrev = ys;

        header = "";
        xs = "";
        ys = "";

        header += "|i          ";
        for (int i = 0; i <= n; i++){
            header += String.format("|%-10d", i);
        }
    }

    public static void makeSimpsonXs(double a, double h, double n){
        xs += "|xi         ";
        if (isLarge) n = 100;
        for (double i = a; i <= a + h*n; i += h){
            xs += String.format("|%-10.5f", i);
        }

        ys += "|yi         ";
    }

    public static void addYToSimpsonTable(double y){
        counterForY++;
        if (counterForY < 102) ys += String.format("|%-10.5f", y);
    }

    public static void printSimpsonTable(int n){
        if (isLarge) n = 102;
        printLines(n);
        Printer.println(headerPrev);
        printLines(n);
        Printer.println(xsPrev);
        printLines(n);
        Printer.println(ysPrev);
        printLines(n);
        if (isLarge) Printer.printErr("Таблица была сокращена до n = 100");
    }

    public static void printLines(int n){
        for (int i = 0; i < n; i++){
            Printer.print("-----------");
        }
        Printer.println("");
    }
}
