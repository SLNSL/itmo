package com.company.tables;

import com.company.Printer;

public class RectangleTable {

    public static String header;
    public static String xs;
    public static String ys;
    public static String xsHalf;
    public static String ysHalf;

    public static String headerPrev;
    public static String xsPrev;
    public static String ysPrev;
    public static String xsHalfPrev;
    public static String ysHalfPrev;

    public static boolean isLarge = false;

    public static int counterForYs = 0;


    public static void makeRectangleHeader(double n){
        if (n > 100) isLarge = true;
        if (isLarge) n = 100;
        counterForYs = 0;

        headerPrev = header;
        xsPrev = xs;
        ysPrev = ys;
        xsHalfPrev = xsHalf;
        ysHalfPrev = ysHalf;

        header = "";
        xs = "";
        ys = "|yi         ";
        xsHalf = "";
        ysHalf = "|yi-(1/2)   |          ";

        header += "|i          ";
        for (int i = 0; i <= n; i++){
            header += String.format("|%-10d", i);
        }
    }

    public static void makeRectangleXs(double a, double h, double n){
        xs += "|xi         ";
        if (isLarge) n = 100;
        for (int i = 0; i <= n; i += 1){
            xs += String.format("|%-10.5f", a + (i * h));
        }
    }

    public static void addYToRectangleTable(double y){
        counterForYs++;
        if (counterForYs < 102) ys += String.format("|%-10.5f", y);
//        ys += String.format("|%-10.5f", y);
    }

    public static void makeRectangleXsHalf(double a, double h, double n){
        xsHalf += "|xi-(1/2)   |          ";
        double i = 0;
        if (isLarge) n = 100;
        for (int j = 1; j <= n; j++){
            i = a + j*h;
            xsHalf += String.format("|%-10.5f", i - (i - (i - h))/2);
        }
    }

    public static void addYHalfToRectangleTable(double y){
        if (counterForYs < 102) ysHalf += String.format("|%-10.5f", y);
//        ysHalf += String.format("|%-10.5f", y);
    }

    public static void printRectangleTable(int n){
        if (isLarge) n = 102;
        printLines(n);
        Printer.println(headerPrev);
        printLines(n);
        Printer.println(xsPrev);
        printLines(n);
        Printer.println(ysPrev);
        printLines(n);
        Printer.println(xsHalfPrev);
        printLines(n);
        Printer.println(ysHalfPrev);
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
