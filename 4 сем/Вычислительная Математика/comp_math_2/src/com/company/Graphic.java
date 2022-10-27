package com.company;


import org.knowm.xchart.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Graphic {

    public static double a;
    public static double b;

    public static Func f;
    public static FuncSystem fS;



    public static void setValues(Func f){
       Graphic.a = -10;
       Graphic.b = 10;
       Graphic.f = f;
    }

    public static void setValues(FuncSystem fS){
        Graphic.a = -9;
        Graphic.b = 9;
        Graphic.fS = fS;
    }

    public static void drawFunction(){
        double k = (b - a) / 200;
        double [] xs = new double[200];
        double [] ys = new double[200];
        double [] y2s = new double[200];

        xs[0] = a;
        ys[0] = f.func(a);


        double currX = xs[0];
        for (int i = 1; i < 200; i++){
            currX += k;
            xs[i] = currX;
            ys[i] = f.func(currX);

        }

        XYChart chart = QuickChart.getChart("График", "X", "Y", "f(x)", xs, ys);

        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

        try {
            BitmapEncoder.saveBitmap(chart, "./График", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public static void drawSystemOfFunctions(){
        double [] xs = new double[8000];
        double [] y2s = new double[4000];

        double k = (b - a) / 4000;

//        xs[0] = a;
//        y2s[0] = fS.getYFromF2(a);

//        double currX = xs[0];
//        for (int i = 1; i < 200; i++){
//            currX += k;
//            xs[i] = currX;
//            y2s[i] = fS.getYFromF2(currX);
//        }
//        XYChart chart = QuickChart.getChart("График", "X", "Y", "f2", xs, y2s);


        double [] y1s = new double[8000];

        xs[0] = a;
        y1s[0] = fS.getYFromF1(a);
        double currX = xs[0];
        for (int i = 1; i < 4000; i++){
            currX += k;
            xs[i] = currX;
            y1s[i] = fS.getYFromF1(currX);
        }
        xs[4000] = a;
        y1s[4000] = -fS.getYFromF1(a);
        currX = xs[4000];
        for (int i = 4000; i < 8000; i++){
            currX += k;
            xs[i] = currX;
            y1s[i] = -fS.getYFromF1(currX);
        }
        XYChart chart = QuickChart.getChart("График", "X", "Y", "f1", xs, y1s);

        xs = new double[4000];
        xs[0] = a;
        y2s[0] = fS.getYFromF2(a);

        currX = xs[0];
        for (int i = 1; i < 4000; i++){
            currX += k;
            xs[i] = currX;
            y2s[i] = fS.getYFromF2(currX);
        }


        chart.addSeries("f2", xs, y2s);


        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

        try {
            BitmapEncoder.saveBitmap(chart, "./График", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
