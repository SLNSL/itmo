package com.company;

import java.util.Vector;

public class Table {

    public static void printXYTable(Data data){
        for (int i = 0; i < data.n + 1; i++) System.out.print("-----------");

        System.out.println();
        System.out.print("|x         ");
        for (int i = 0; i < data.n; i++){
            System.out.printf("|%-10.5f", data.x[i]);
        }
        System.out.println();
        for (int i = 0; i < data.n + 1; i++) System.out.print("-----------");
        System.out.println();
        System.out.print("|y         ");
        for (int i = 0; i < data.n; i++){
            System.out.printf("|%-10.5f", data.y[i]);
        }
        System.out.println();
        for (int i = 0; i < data.n + 1; i++) System.out.print("-----------");
        System.out.println();
    }


//    public static void printYsTable(Data data, Vector<Double>[] ys){
//        for (int i = 0; i < data.n + 1; i++) System.out.print("-----------");
//        System.out.println();
//        System.out.print("|№         |xi        |yi        ");
//        for (int i = 2; i < data.n; i++){
//            System.out.print("|Δ" + i + "yi      ");
//        }
//        System.out.println();
//        for (int i = 0; i < data.n + 1; i++){
//            System.out.printf("|%10s|%10.5f|%10.5f|%10.5f|%10.5f|%10.5f|%10.5f|");
//        }
//    }
}
