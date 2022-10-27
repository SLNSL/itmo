package com.company;

public class Data {
    int n;
    double[] x;
    double[] y;
    double x0;

    public Data(double x0, double[] x, double[] y){
        this.x = x;
        this.y = y;
        this.n = x.length;
        this.x0 = x0;
    }
}
