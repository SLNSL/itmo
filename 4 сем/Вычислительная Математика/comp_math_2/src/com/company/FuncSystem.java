package com.company;

public class FuncSystem {

    //f1(x1, x2) = 0.1(x1)^2 + 0.9(x2)^2 = 0
    //f2(x1, x2) = -0.1(x1)^2 + (x1) + 0.2 = 0

    public int n;

    final double PHI = (1 + Math.sqrt(5)) / 2;

    public FuncSystem(int n) {
        this.n = n;
    }

    public double func1(double x1, double x2) {

        return -0.1*x1*x1 - 0.2*x2*x2 + 0.3;

    }

    public double func2(double x1, double x2) {

        return 0.2*x1*x1 - 0.1*x1 + 0.4;

    }

    public double getYFromF1(double x) {
        return Math.sqrt((0.3 - 0.1*x*x - x)/0.2);

    }

    public double getYFromF2(double x) {
        return 0.2*x*x - 0.1*x + 0.4;

    }

    public double func1DerFromX1(double x1) {
        return -0.2*x1;
    }

    public double func1DerFromX2(double x2) {
        return -0.4*x2;

    }

    public double func2DerFromX1(double x1) {
        return 0.4*x1 - 0.1;

    }

    public double func2DerFromX2(double x2) {
        return 0;
    }


    public double findMaxInFi1FromX1(double a, double b, double e){
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (func1DerFromX1(x1) <= func1DerFromX1(x2))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
//        System.out.println("f1x1 max = " + (a + b) / 2);
        return func1DerFromX1((a + b) / 2);
    }

    public double findMaxInFi1FromX2(double a, double b, double e){
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (func1DerFromX2(x1) <= func1DerFromX2(x2))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
//        System.out.println("f1x2 max = " + (a + b) / 2);
        return func1DerFromX2((a + b) / 2);
    }

    public double findMaxInFi2FromX1(double a, double b, double e){
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (func2DerFromX1(x1) <= func2DerFromX1(x2))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
//        System.out.println("f2x1 max = " + (a + b) / 2);
        return func2DerFromX1((a + b) / 2);
    }

    public double findMaxInFi2FromX2(double a, double b, double e){
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
//            System.out.println(x1 + " " + x2);
            if (func2DerFromX2(x1) <= func2DerFromX2(x2))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
//        System.out.println("f2x2 max = " + (a + b) / 2);
        return func2DerFromX2((a + b) / 2);
    }

    public double getMaxFi(double x1a, double x1b, double x2a, double x2b, double e) {

        double fi1 = Math.abs(findMaxInFi1FromX1(x1a, x1b, e)) + Math.abs(findMaxInFi1FromX2(x2a, x2b, e));
        double fi2 = Math.abs(findMaxInFi2FromX1(x1a, x1b, e)) + Math.abs(findMaxInFi2FromX2(x2a, x2b, e));

        double maxFi = Math.max(fi1, fi2);

        return maxFi;
    }

}
