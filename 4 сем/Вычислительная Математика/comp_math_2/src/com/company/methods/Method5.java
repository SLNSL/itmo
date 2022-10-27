package com.company.methods;

import com.company.*;
import com.company.workers.Printer;

public class Method5 extends Method {

    public Method5(Data data, Func f) {
        super(data, f);
    }

    @Override
    public void start() {
        double lambda = -1 / f.funcDer(f.getXStart(data.a, data.b));

        double xi = f.getXStart(data.a, data.b);
        double xiPlus1 = f.fi(xi, lambda);
        double abs = Math.abs(xiPlus1 - xi);

        if (!check(data.a, data.b, f)) System.exit(1);

        double q = f.getMaxQ(data.a, data.b, data.e, lambda);




//        if (q < 0 || q > 1) {
//            Printer.println("q не входит в диапазон 0 <= q < 1. q = " + q);
//            System.exit(1);
//        }


        if (q >= 0 && q <= 0.5) {
            Printer.println("Критерий окончания итерационного процесса равен e = " + data.e);
        } else {

            data.e = data.e * (1 - q) / q;
            Printer.println("Критерий окончания итерационного процесса равен (1 - q)/q * e = " + data.e);
        }

        do {
            Table.Method5.draw(xi, xiPlus1, f.fi(xiPlus1, lambda), f.func(xiPlus1), abs);
            xi = xiPlus1;
            xiPlus1 = f.fi(xi, lambda);
            abs = Math.abs(xiPlus1 - xi);
        } while (abs >= data.e);
        Table.Method5.draw(xi, xiPlus1, f.fi(xiPlus1, lambda), f.func(xiPlus1), abs);


        Printer.println("x* = " + xi);
        Printer.println("f(x*) = " + f.func(xi));




    }



        @Override
        public boolean check(double a, double b, Func f) {

        for (double i = a; i <= b - 0.001; i += 0.001) {
//            System.out.println(f.func(i) + " " + f.func(i + 0.001));
            if (f.func(i) > 0 && f.func(i + 0.001) < 0 ||
                    f.func(i) < 0 && f.func(i + 0.001) > 0) return true;
        }
        Printer.println("На данном промежутке нет корней");
        return false;
    }
}
