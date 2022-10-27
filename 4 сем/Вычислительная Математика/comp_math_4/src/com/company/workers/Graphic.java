package com.company.workers;

import java.io.IOException;
import java.math.BigDecimal;

import com.company.Data;
import com.company.methods.*;
import com.company.methods.abstracts.Method;
import org.knowm.xchart.*;

public class Graphic {



    public static void drawFunctions(Data data, ExpFunc eF,
                                     LinearFunc linF, LogFunc lgF,
                                     PowerFunc pF, SecDegFunc sF,
                                     ThirdDegFunc tF){
        double a = data.xs[1].doubleValue() - 1;
        double b = data.xs[data.n].doubleValue() + 1;
        double k = (b - a) / 100;
        double [] xs = new double[100];

        double [] ysEf = new double[100];
        double [] ysLinF = new double[100];
        double [] ysLgF = new double[100];
        double [] ysPF = new double[100];
        double [] ysSF = new double[100];
        double [] ysTF = new double[100];

        xs[0] = a;
        if (eF.sig != null) ysEf[0] = eF.fi(BigDecimal.valueOf(a)).doubleValue();
        if (lgF.sig != null) ysLgF[0] = lgF.fi(BigDecimal.valueOf(a)).doubleValue();
        if (pF.sig != null) ysPF[0] = pF.fi(BigDecimal.valueOf(a)).doubleValue();

        ysLinF[0] = linF.fi(BigDecimal.valueOf(a)).doubleValue();
        ysSF[0] = sF.fi(BigDecimal.valueOf(a)).doubleValue();
        ysTF[0] = tF.fi(BigDecimal.valueOf(a)).doubleValue();


        double currX = xs[0];
        for (int i = 1; i < 100; i++){
            currX += k;
            xs[i] = currX;
            if (eF.sig != null) ysEf[i] = eF.fi(BigDecimal.valueOf(currX)).doubleValue();
            if (lgF.sig != null) ysLgF[i] = lgF.fi(BigDecimal.valueOf(currX)).doubleValue();
            if (pF.sig != null)  ysPF[i] = pF.fi(BigDecimal.valueOf(currX)).doubleValue();

            ysLinF[i] = linF.fi(BigDecimal.valueOf(currX)).doubleValue();
            ysSF[i] = sF.fi(BigDecimal.valueOf(currX)).doubleValue();
            ysTF[i] = tF.fi(BigDecimal.valueOf(currX)).doubleValue();

        }

        XYChart chart = QuickChart.getChart("График", "X", "Y", "y = ax + b", xs, ysLinF);
        if (eF.sig != null) chart.addSeries("y = a*e^(xb)", xs, ysEf);
        if (lgF.sig != null) chart.addSeries("y = a*lnx + b", xs, ysLgF);
        if (pF.sig != null) chart.addSeries("y = a * b^x", xs, ysPF);
        chart.addSeries("y = ax^2 + bx + c", xs, ysSF);
        chart.addSeries("y = ax^3 + bx^2 + cx + d", xs, ysTF);

        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

        try {
            BitmapEncoder.saveBitmap(chart, "./График", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e){
            e.printStackTrace();
        }


    }

}
