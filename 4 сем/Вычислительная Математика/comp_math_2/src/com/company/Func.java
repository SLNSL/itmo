package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Func {
    public int n;
    public int solveType;
    final double PHI = (1 + Math.sqrt(5)) / 2;

    public Func(int n){
        this.n = n;
    }

    public double func(double x){
        return switch (n) {
            case 1 ->
                    // x^3 - x + 4
                    Math.pow(x, 3) - x + 4;
            case 2 ->
                    // x^3 - 0.77x^2 -1.251x +0.43
                    Math.pow(x, 3) - 0.77 * Math.pow(x, 2) - 1.251 * x + 0.43;
            case 3 ->
                    // sin(x) + 0.525
                    Math.sin(x) + 0.5;
            default -> 123456789;
        };
    }

    public double funcDer(double x){
        return switch (n) {
            case 1 ->
                    // 3 x^2 - 1
                    3 * Math.pow(x, 2) - 1;
            case 2 ->
                    // 3x^2 - 1.54x - 1.251
                    3 * Math.pow(x, 2) - 1.54 * x - 1.251;
            case 3 ->
                    // sin(x) + 0.525
                    Math.cos(x);
            default -> 123456789;
        };
    }

    public double funcDerDer(double x){
        return switch (n) {
            case 1 ->
                    6 * x;
            case 2 ->
                    6 * x - 1.54;
            case 3 ->
                    -Math.sin(x);
            default -> 123456789;
        };
    }



    public double getXStart(double a, double b){

        if (solveType == 5) {
            if (funcDer(a) > funcDer(b)) return a;
            return b;
        }
        if (solveType == 3){
            for (double i = a; i <= b - 0.05; i += 0.05){
                if (func(i) * funcDerDer(i) > 0) return i;
            }
            return a;
        }

        return 123456789;
    }

    public double getMaxQ(double a, double b, double e, double lambda){
        double maxPointInFiDer= getMaxPointInFiDer(a, b, e, lambda);
//        System.out.println("maxPoint = " + maxPointInFiDer);
        if (maxPointInFiDer >= 1){
            System.out.println("Не выполняется досточное условие сходимости. max fi'(x) = " + maxPointInFiDer);
            System.exit(1);
        }
        return maxPointInFiDer;

    }

    public double fi(double x, double lambda){
        return x + lambda * func(x);
    }

    public double fiDer(double x, double lambda){
        return 1 + lambda * funcDer(x);
    }

    public double fiDerDer(double x, double lambda){
        return lambda * funcDerDer(x);
    }

//    public double getMaxPointInFiDer(double a, double b, double lambda){
//        double max = Double.MIN_VALUE;
//        for (double i = a; i <= b - 0.05; i += 0.05){
//
//            if (fiDerDer(i, lambda) > 0 && fiDerDer(i + 0.05, lambda) < 0){
//
//                if (max < fiDer(i + 0.025, lambda) && fiDer(i + 0.025, lambda) < 1) {
//                    System.out.println("ex x = " + i + 0.025);
//                    max = fiDer(i + 0.025, lambda);
//                }
//            }
//        }
//        if (max == Double.MIN_VALUE){
//            return (Math.max(fiDer(a, lambda), fiDer(b, lambda)));
//        }
//        return max;
//    }

    public double getMaxPointInFiDer(double a, double b, double e, double lambda){
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (fiDer(x1, lambda) <= fiDer(x2, lambda))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
        return fiDer((a + b) / 2, lambda);
    }
}
