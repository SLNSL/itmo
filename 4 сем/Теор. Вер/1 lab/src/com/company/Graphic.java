package com.company;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.ChartBuilder;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.util.List;
import java.util.Vector;

import static org.knowm.xchart.style.lines.SeriesLines.SOLID;

public class Graphic{

    private XYChart chart;
    private Vector<Double> xs = new Vector<>();
    private Vector<Double> ys = new Vector<>();

    public Graphic(String title, String x, String y){
        chart = new XYChartBuilder()
                .title(title)
                .xAxisTitle(x)
                .yAxisTitle(y)
                .build();

    }

    public void addHorizontalLine(String name, double a, double b, double h){
        Vector<Double> xs = new Vector<>();
        Vector<Double> ys = new Vector<>();
        xs.add(a);
        xs.add(b);
        ys.add(h);
        ys.add(h);
        chart.getStyler().setMarkerSize(0);
        chart.addSeries(name, xs, ys).setLineWidth(3);
    }

    public void addPoint(double x, double p){
        xs.add(x);
        ys.add(p);
        chart.getStyler().setMarkerSize(0);
    }

    public void addRect(String name, double a, double b, double p){
        Vector<Double> xs = new Vector<>();
        Vector<Double> ys = new Vector<>();
        xs.add(a);
        xs.add(b);
        ys.add(p);
        ys.add(p);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        chart.getStyler().setMarkerSize(0);
        chart.addSeries(name, xs, ys).setLineWidth(3);
    }

    public void print(){
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }

    public void printPolygon(String name){
        chart.addSeries(name, xs, ys).setLineWidth(3);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }


}
