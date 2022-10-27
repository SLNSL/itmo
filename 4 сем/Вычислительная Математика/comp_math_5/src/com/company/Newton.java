package com.company;

import java.util.Arrays;
import java.util.Vector;

public class Newton {
    public Data data;
    public Vector<Double>[] ys;
    public double[] x;
    public int k = 0;

    public Newton(Data data) {
        this.data = data;
        ys = new Vector[data.n];
        for (int i = 0; i < data.n; i++) ys[i] = new Vector<>();
    }

    public void run() {
        System.out.println("N = " + getY(data.x0));
    }

    public double getY(double x0){
        int n = data.n;
        x = data.x;
        double[] y = data.y;

        for (int i = 0; i < n; i++) {
            ys[0].add(y[i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (ys[i - 1].size() <= j + 1) break;
                ys[i].add(ys[i - 1].get(j + 1) - ys[i - 1].get(j));

            }
        }
        if (k == 0) {
        System.out.println("Таблица конечных разностей\n____________________");

            for (Vector<Double> p : ys) {
                for (Double l : p) {
                    System.out.printf("%.4f ", l);
                }
                System.out.println();
            }
            System.out.println("________________");
        }



        double xi = 0;
        int ind = 0;

        for (int i = 0; i < x.length; i++) {
            if (x[i] < x0) {
                ind = i;
            } else {
                break;
            }
        }
        if (ind < x.length / 2) {
            return forward(ind, x0);
        } else {
            ind = n - 1;
            return backward(ind, x0);
        }


    }




    public double forward(int ind, double x_) {
        double n = 0;
        int j = 0;
        if (k == 0) System.out.println("Вперед");
        double t = (x_ - x[ind]) / (x[1] - x[0]);
        for (int i = ind; i < x.length; i++) {
            if (k == 0) {
                System.out.printf("%.4f * %.4f  +  ", calculateTForward(j, t),ys[j].get(ind));
            }
            n += calculateTForward(j, t) * ys[j].get(ind);
            j++;
        }
        if (k == 0)System.out.println();

        k++;
        return n;
    }

    public double backward(int ind, double x_) {
        double n = 0;
        int j = 0;
        if (k == 0) System.out.println("назад");
        double t = (x_ - x[ind]) / (x[1] - x[0]);

        for (int i = ind; i >= 0; i--) {
            if (k == 0) {
                System.out.printf("%.4f * %.4f  +  ", calculateTBackward(j, t) , ys[j].get(i));
            }
            n += calculateTBackward(j, t) * ys[j].get(i);
            j++;
        }
        if (k == 0) System.out.println();

        k++;
        return n;
    }


    public double calculateTForward(int n, double t) {
        double s = 1;
        for (int i = 0; i < n; i++) {
            s *= t - i;
        }

        for (int i = 1; i <= n; i++) {
            s /= i;
        }

        return s;
    }

    public double calculateTBackward(int n, double t) {
        double s = 1;
        for (int i = 0; i < n; i++) {
            s *= t + i;
        }

        for (int i = 1; i <= n; i++) {
            s /= i;
        }

        return s;
    }
}
