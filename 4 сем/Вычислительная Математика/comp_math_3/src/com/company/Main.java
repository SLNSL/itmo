package com.company;

import com.company.methods.Method;
import com.company.methods.Rectangle;
import com.company.methods.Simpson;

public class Main {

    public static void main(String[] args) {
//        int a = 1;
//        int b = 2;
//        int n = 4;
//        double e = 0.1;
//        Func func = new Func(1, a, b);
//        Simpson simpson = new Simpson(n, e, func);
//        double ans = simpson.run();
//        Printer.println(ans);


//        int a = 1;
//        int b = 2;
//        int n = 10;
//        double e = 0.9;
//        Func func = new Func(1, a, b);
//        Rectangle rectangle = new Rectangle(n, e, func);
//        rectangle.run();
        System.out.println("Вычислить точный инетграл");
        int nOfFunction = Asker.askNumberOfFunction();
        int a = Asker.askA();
        int b = Asker.askB();
        Func func = new Func(nOfFunction, a, b);

        int n = 6;
        double e = Asker.askE();

        int nOfMethod = Asker.askMethod();

        Method method = Method.createMethod(nOfMethod, n, e, func);
        method.run();

    }
}
