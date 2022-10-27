package com.company;

public class Functions {

    public static Data makeValues(int nOfFunc, double x0, double xl, double xr, int n){
        return switch (nOfFunc) {
            case 1 -> sin(x0, xl, xr, n);
            case 2 -> cos(x0, xl, xr, n);
            case 3 -> sqrt(x0, xl, xr, n);
            default -> null;
        };
    }

    public static Data sin(double x0, double xl, double xr, int n){
        double[] x = new double[n];
        double[] y = new double[n];

        double h = (xr - xl) / (n - 1);

        for (int i = 0; i < n; i++){
            x[i] = xl + i * h;
            y[i] = Math.sin(x[i]);
        }
        return new Data(x0, x, y);
    }


    public static Data cos(double x0, double xl, double xr, int n){
        double[] x = new double[n];
        double[] y = new double[n];

        double h = (xr - xl) / (n - 1);

        for (int i = 0; i < n; i++){
            x[i] = xl + i * h;
            y[i] = Math.cos(x[i]);
        }
        return new Data(x0, x, y);
    }

    public static Data sqrt(double x0, double xl, double xr, int n){
        double[] x = new double[n];
        double[] y = new double[n];

        double h = (xr - xl) / (n - 1);

        for (int i = 0; i < n; i++){
            x[i] = xl + i * h;
            y[i] = Math.sqrt(x[i]);
        }
        return new Data(x0, x, y);
    }

}
