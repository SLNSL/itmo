package com.company.methods;

import com.company.Func;
import com.company.Printer;
import com.company.tables.SimpsonTable;

public class Simpson extends Method{

    private int n;
    private Func func;
    private double e;

    public Simpson(int n, double e, Func f){

        this.n = n;
        this.e = e;
        this.func = f;
    }


    public double runCycle(){
        SimpsonTable.makeSimpsonHeader(n);
        double a = func.a;
        double b = func.b;
        double h = (b - a) / n;
        SimpsonTable.makeSimpsonXs(a, h, n);
        double oddYs = 0;
        double evenYs = 0;

        SimpsonTable.addYToSimpsonTable(func.f(a));

        for (int i = 1; i < n - 1; i += 2){
            oddYs += func.f(a + i * h);
            SimpsonTable.addYToSimpsonTable(func.f(a + i * h));
            evenYs += func.f(a + (i+1) * h);
            SimpsonTable.addYToSimpsonTable(func.f(a + (i+1) * h));
        }
        oddYs += func.f(a + (n-1) * h);
        SimpsonTable.addYToSimpsonTable(func.f(a + (n-1) * h));

        SimpsonTable.addYToSimpsonTable(func.f(a + (n) * h));

        return h/3 * (func.f(a) + 4*oddYs + 2*evenYs + func.f(b));
    }

    @Override
    public void run(){

        double s = runCycle();
        double s2 = 0;

        do {
            s2 = s;
            n = n * 2;
            s = runCycle();
        } while (Math.abs(s - s2)/15 > e);
        SimpsonTable.printSimpsonTable(n/2 + 2);
        Printer.println("");
        Printer.println("Количество разбиений n = " + n/2);
        Printer.println("");
        double I = func.getRealIntegral(func.b) - func.getRealIntegral(func.a);
        Printer.println("ΔI = " + Math.abs(s2 - I));
        Printer.println("I = " + s2);
    }



}
