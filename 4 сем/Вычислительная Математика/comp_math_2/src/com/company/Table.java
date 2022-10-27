package com.company;

import com.company.workers.Printer;

public class Table {
    public static int n = 0;


    public static class Method5 {
        private static void drawHeader(){
            Printer.println("-------------------------------------------------------------------");
            Printer.println("|n         |xi        |xi+1      |fi(xi+1)  |f(xi+1)   |abs       |");
            Printer.println("-------------------------------------------------------------------");
        }
        public static void draw(double xi, double xiPlus1, double fiX, double fX, double abs){
            if (n == 0) drawHeader();
            n++;
            Printer.printf("|%-10d|%-10.5f|%-10.5f|%-10.5f|%-10.5f|%-10.5f|\n",
                    n, xi, xiPlus1, fiX, fX, abs);
            Printer.println("-------------------------------------------------------------------");

        }
    }

    public static class Method3{
        private static void drawHeader(){
            Printer.println("-------------------------------------------------------------------");
            Printer.println("|n         |xi        |f(xi)     |f'(xi)    |xi+1      |abs       |");
            Printer.println("-------------------------------------------------------------------");
        }
        public static void draw(double xi, double fX, double fDerX, double xiPlus1, double abs){
            if (n == 0) drawHeader();
            n++;
            Printer.printf("|%-10d|%-10.5f|%-10.5f|%-10.5f|%-10.5f|%-10.5f|\n",
                    n, xi, fX, fDerX, xiPlus1, abs);
            Printer.println("-------------------------------------------------------------------");

        }
    }

    public static class Method7{
        public static void draw(double x1, double x2, double x1Prev, double x2Prev){
            n++;
            Printer.println(n + " шаг.");
            Printer.println("   x1(" + n + ") = " + x1);
            Printer.println("   x2(" + n + ") = " + x2);
            Printer.println("   |x1(" + n + ") - x1(" + (n-1) + ")|= " + Math.abs(x1 - x1Prev));
            Printer.println("   |x2(" + n + ") - x2(" + (n-1) + ")|= " + Math.abs(x2 - x2Prev));
        }
    }


}
