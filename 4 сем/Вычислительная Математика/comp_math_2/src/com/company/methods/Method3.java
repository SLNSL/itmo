package com.company.methods;

import com.company.Data;
import com.company.Func;
import com.company.Table;
import com.company.workers.Printer;

public class Method3 extends Method{

    public Method3(Data data, Func f){
        super(data, f);
    }

    @Override
    public void start() {
        if (!check(data.a, data.b, f)) System.exit(1);



        double xStart = f.getXStart(data.a, data.b);
        double xStartPlus1 = xStart - f.func(xStart)/f.funcDer(xStart);
        double abs = Math.abs(xStartPlus1 - xStart);

        while (abs > data.e){
            Table.Method3.draw(xStart, f.func(xStart), f.funcDer(xStart), xStartPlus1, abs);
            xStart = xStartPlus1;
            xStartPlus1 = xStart - f.func(xStart)/f.funcDer(xStart);
            abs = Math.abs(xStartPlus1 - xStart);
        }
        Table.Method3.draw(xStart, f.func(xStart), f.funcDer(xStart), xStartPlus1, abs);
        xStart = xStartPlus1;
        Printer.println("x* = " + xStart);
        Printer.println("f(x*) = " + f.func(xStart));

    }

    @Override
    public boolean check(double a, double b, Func f) {
        if (f.func(data.a) * f.func(data.b) >= 0){
            Printer.println("Функция имеет одинаковые знаки на концах отрезка [" + data.a + "; " + data.b + "].");
            return false;
        }


        for (double i = a; i <= b - 0.001; i += 0.001){
            if (f.funcDer(i) * f.funcDer(i + 0.001) < 0) {
                Printer.println("Производная f'(x) не сохраняет знак на отрезке [" + data.a + "; " + data.b + "].");
                return false;
            }
            if (f.funcDer(i) * f.funcDer(i + 0.001) < 0) {
                Printer.println("Производная f''(x) не сохраняет знак на отрезке [" + data.a + "; " + data.b + "].");
                return false;
            }
        }



        for (double i = a; i <= b - 0.001; i += 0.001) {
            if (f.func(i) > 0 && f.func(i + 0.001) < 0 ||
                    f.func(i) < 0 && f.func(i + 0.001) > 0) return true;
        }
        Printer.println("На данном промежутке нет корней.");
        return false;
    }
}
