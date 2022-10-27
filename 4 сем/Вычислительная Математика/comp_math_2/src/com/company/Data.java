package com.company;

public class Data {
    public double a;
    public double b;
    public double e;
    public double x1a;
    public double x1b;
    public double x2a;
    public double x2b;

    public Data(double a, double b, double e){
        this.a = a;
        this.b = b;
        this.e = e;
    }

    public Data(double e, double x1a, double x1b, double x2a, double x2b){
        this.e = e;
        this.x1a = x1a;
        this.x1b = x1b;
        this.x2a = x2a;
        this.x2b = x2b;
    }
}
