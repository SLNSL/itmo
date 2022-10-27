package com.company;

public class Func {

    public int n;
    public int a;
    public int b;

    public Func(int n, int a, int b){
        this.n = n;
        this.a = a;
        this.b = b;
    }

    public double f(double x){
        return switch (n){
            case 1 -> 2*x*x*x - 3*x*x + 5*x -9;
            case 2 -> Math.pow(x, 5) + 0.2*x;
            case 3 -> 2 * x*x*x - 3*x*x + 5*x - 9;
            case 4 -> 10 * Math.pow(x, 8);
            default -> 1234567890;
        };
    }

    public double getRealIntegral(double x){
        return switch (n){
            case 1 -> (x*x*x*x - 2*x*x*x + 5*x*x - 18*x)/2;
            case 2 -> Math.pow(x, 6) / 6 + Math.pow(x, 2) / 10;
            case 3 -> (x * (Math.pow(x, 3) - 2 * x*x + 5*x - 18)) / 2;
            case 4 -> 10 * Math.pow(x, 9) / 9;
            default -> 124567890;
        };
    }
}
