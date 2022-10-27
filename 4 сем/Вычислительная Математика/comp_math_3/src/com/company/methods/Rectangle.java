package com.company.methods;

import com.company.Func;
import com.company.Printer;
import com.company.tables.RectangleTable;
import com.company.wrappers.ResultPack;

public class Rectangle extends Method{

    private int n;
    private double e;
    private Func func;

    public Rectangle(int n, double e, Func func){
        this.n = n;
        this.e = e;
        this.func = func;
    }

    public ResultPack runCycle(){
        double a = func.a;
        double b = func.b;
        RectangleTable.makeRectangleHeader(n);
        double h = (b-a) / n;
        RectangleTable.makeRectangleXs(a, h, n);
        RectangleTable.makeRectangleXsHalf(a, h, n);

        double x = a;
        double y;
        double xHalf;
        double yHalf;

        double resL = 0, resC = 0, resR = 0;
        for (int i = 0; i <= n; i += 1){
            y = func.f(x);
            RectangleTable.addYToRectangleTable(y);
            x += h;

            if (i > 0){
                xHalf = x - 1.5f * h;
                yHalf = func.f(xHalf);
                resC += yHalf;
                resR += y;
                RectangleTable.addYHalfToRectangleTable(yHalf);
            }

            if (i < n) resL += y;


        }
        resC *= h;
        resL *= h;
        resR *= h;

        ResultPack rsp = new ResultPack(resL, resC, resR);
        return rsp;
    }

    @Override
    public void run(){
        ResultPack rsp = runCycle();
        ResultPack rsp2;

        do {
            rsp2 = rsp;
            n *= 2;
            rsp = runCycle();

        } while (Math.abs(rsp2.resL - rsp.resL)/(3) > e || Math.abs(rsp2.resC - rsp.resC)/3 > e || Math.abs(rsp2.resR - rsp.resR)/3 > e);

        RectangleTable.printRectangleTable(n/2 + 2);
        Printer.println("");
        Printer.println("Число разбиений n = " + n/2);
        Printer.println("");
        double I = func.getRealIntegral(func.b) - func.getRealIntegral(func.a);
        Printer.println("ΔI_сред = " + Math.abs(rsp2.resC - I));
        Printer.println("ΔI_лев = " + Math.abs(rsp2.resL - I));
        Printer.println("ΔI_прав = " + Math.abs(rsp2.resR - I));
        Printer.println("");

        Printer.println("I_сред = " + rsp2.resC);
        Printer.println("I_лев = " + rsp2.resL);
        Printer.println("I_прав = " + rsp2.resR);
    }

}
