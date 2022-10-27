package com.company;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.awt.*;

public class Graphic {
    Newton newton;
    Lagrange lagrange;
    Data data;

    public Graphic(Newton newton, Data data, Lagrange lagrange){
        this.data = data;
        this.newton = newton;
        this.lagrange = lagrange;
    }

    public void draw(){
        double a = data.x[0];
        double b = data.x[data.n - 1];

        int n = data.n * 10;
        double k = (b - a) / (n);

        double curX = a;

        double[] x = new double[n + 1];
        double[] yNewton = new double[n + 1];
        double[] yLagrange = new double[n + 1];

        for (int i = 0; i <= n; i++){
            x[i] = curX;
            yNewton[i] = newton.getY(curX);
            yLagrange[i] = lagrange.getY(curX);
            curX += k;
        }


        double[] xFN = new double[1];
        double[] yFN = new double[1];
        xFN[0] = data.x0;
        yFN[0] = newton.getY(data.x0);
        XYChart chart = QuickChart.getChart("Ньютон", "x", "y", "Ньютон", x, yNewton);
        chart.addSeries("Заданная таблица", data.x, data.y);
        chart.addSeries("Искомая точка", xFN, yFN).setMarkerColor(Color.RED).setLineColor(Color.RED);

        double[] xFL = new double[1];
        double[] yFL = new double[1];
        xFL[0] = data.x0;
        yFL[0] = lagrange.getY(data.x0);
        XYChart chart2 = QuickChart.getChart("Лагранж", "x", "y", "Лагранж", x, yLagrange);
        chart2.addSeries("Заданная таблица", data.x, data.y);
        chart2.addSeries("Искомая точка", xFL, yFL).setMarkerColor(Color.RED).setLineColor(Color.RED);





        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

        SwingWrapper swingWrapper2 = new SwingWrapper(chart2);
        swingWrapper2.displayChart();


    }


}
