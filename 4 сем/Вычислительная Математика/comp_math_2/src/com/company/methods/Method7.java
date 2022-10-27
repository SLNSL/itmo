package com.company.methods;

import com.company.Data;
import com.company.Func;
import com.company.FuncSystem;
import com.company.Table;
import com.company.workers.Printer;

public class Method7{

    private Data data;
    private FuncSystem fS;

    public Method7(Data data, FuncSystem fS) {
        this.data = data;
        this.fS = fS;
    }


    public void start() {
        Printer.println("Выполняется метод простых итераций для систем нелинейных уравнений...");
        double x1 = data.x1b;
        double x2 = data.x2b;
        double x1Prev;
        double x2Prev;

        double maxFi = fS.getMaxFi(data.x1a, data.x1b, data.x2a, data.x2b, data.e);
        if (maxFi >= 1) {
            Printer.println("Не выполняется условие сходимости. max fi = " + maxFi);
            System.exit(1);
        }
        do {
            x1Prev = x1;
            x2Prev = x2;
            x1 = fS.func1(x1Prev, x2Prev);
            x2 = fS.func2(x1Prev, x2Prev);
            Table.Method7.draw(x1, x2, x1Prev, x2Prev);
        } while (Math.abs(x1 - x1Prev) >= data.e || Math.abs(x2 - x2Prev) >= data.e);
            Printer.println("x1* = " + x1);
            Printer.println("x2* = " + x2);


    }



}
