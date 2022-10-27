package com.company;

import java.util.Arrays;
import java.util.Vector;

public class Func {

    public static double[] a;
    public static Vector<Integer> ns = new Vector<>();
    public static Vector<Double> ps = new Vector<>();
    public static Vector<Double> xs = new Vector<>();

    public static double mx = 0;
    public static double dx = 0;
    public static double sig = 0;

    public static void printArray(double[] a){
        for (double i: a){
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void getVars(){
        Arrays.sort(a);
        System.out.print("Вариационный ряд:\n     ");
        printArray(a);
        System.out.println();
    }

    public static void getExtremeValues(){
        System.out.println("Минимальное значение = " + a[0]);
        System.out.println("Максимальное значение = " + a[a.length - 1]);
        System.out.println();
    }

    public static void getSampleSize(){
        System.out.println("Размах выборки = " + (a[a.length - 1] - a[0]));
        System.out.println();
    }

    public static void getMX(){
        double[] b = Arrays.stream(a)
                .distinct()
                .toArray();

        for (double bi : b){
            int count = 0;

            for (double ai : a)
                if (ai == bi) count++;


            xs.add(bi);
            ns.add(count);
            ps.add((double)count / a.length);
        }


        for (int i = 0; i < xs.size(); i++){
            mx += xs.get(i) * ns.get(i);
        }
        mx /= a.length;
        System.out.printf("Мат. ожидание = %.5f\n", mx);
    }

    public static void getDX(){
        for (int i = 0; i < xs.size(); i++){
            dx += Math.pow(xs.get(i) - mx, 2) * ns.get(i);
        }
        dx /= a.length;
        System.out.printf("Дисперсия = %.5f\n", dx);
    }

    public static void getSig(){
        sig = Math.sqrt(dx);
        System.out.printf("Среднеквадр. отклонение = %.5f\n\n", sig);
    }

    public static void getEmpiricFunc(){
        Graphic graphic = new Graphic("Эмпирическая функция", "X", "Y");

        double p = 0;
        System.out.println("Эмпирическая функция:");
        System.out.println("    " + 0.000 + ", x <= " + xs.get(0));
        graphic.addHorizontalLine("x <= " + xs.get(0), xs.get(0) - 1, xs.get(0), 0);
        for (int i = 0; i < xs.size() - 1; i++){
            System.out.print("    ");
            p += ps.get(i);
            graphic.addHorizontalLine(xs.get(i) + " < x <= " + xs.get(i + 1), xs.get(i), xs.get(i + 1), p);
            System.out.printf("%.3f, %.3f < x <= %.3f\n", p, xs.get(i), xs.get(i + 1));
        }
        p += ps.get(xs.size() - 1);
        graphic.addHorizontalLine(xs.get(xs.size() - 1) + " < x", xs.get(xs.size() - 1), xs.get(xs.size() - 1) + 1, p);
        System.out.printf("    %.3f, %.3f < x\n\n", p, xs.get(xs.size() - 1));
        graphic.print();

    }

    public static void getVectors(){
        System.out.println("|xi      |ni  |pi      |");
        for (int i = 0; i < xs.size(); i++){
            System.out.printf("|%-8.3f|%-4d|%-8.3f|\n", xs.get(i), ns.get(i), ps.get(i));
        }
        System.out.println();
    }

    public static void getStaticPol(){
        System.out.println("Полигон частот:");
        Graphic polygon = new Graphic("Полигон частот", "Xi", "Ni");
        Graphic gisto = new Graphic("Гистограмма частот", "Xi", "Ni");

        double h = (a[a.length - 1] - a[0])/(1 + Math.log(a.length) / Math.log(2));

        double x0 = a[0] - h/2;


        int k = (int) Math.ceil(1 + Math.log(a.length) / Math.log(2));
        gisto.addRect("y = 0", x0, x0  + k*h, 0);
        for (int i = 0; i < k; i++){
            int count = 0;
            for (double ai : a) if (ai >= x0 && ai < (x0 + h)) count++;

            polygon.addPoint(x0 + h / 2, count);
            gisto.addRect("[" + x0 + " : " + (x0 + h) + ")", x0, x0 + h, count);

            System.out.println("[" + x0 + " : " + (x0 + h) + ") ni = " + count);
            x0 += h;


        }
        polygon.printPolygon("Полигон частот");
        gisto.print();
    }
}
