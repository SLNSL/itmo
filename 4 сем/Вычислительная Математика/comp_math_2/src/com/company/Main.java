package com.company;

import com.company.methods.Method3;
import com.company.methods.Method5;
import com.company.methods.Method7;
import com.company.workers.Printer;
import com.company.workers.Reader;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        Reader.isFile = false;
        System.out.println("Ввод с консоли: 1");
        System.out.println("Ввод с файла: 2");
        boolean isFile = (Integer.parseInt(Reader.readLine()) == 2);

        System.out.println("Вывод в консоль: 1");
        System.out.println("Вывод в файл: 2");
        boolean outIsFile = (Integer.parseInt(Reader.readLine()) == 2);
        Reader.isFile = isFile;
        Printer.isFile = Reader.isFile;

        if (!Reader.isFile) System.out.println("Решить систему уравнений методом простых итераций: 1");
        if (!Reader.isFile) System.out.println("Решить нелинейное уравнение: 2");
        boolean isSystem = (Integer.parseInt(Reader.readLine()) == 1);



        Printer.isFile = outIsFile;
        if (!isSystem) {
            if (!Reader.isFile) {
                Printer.println("1-ая функция: x^3 - x + 4 = 0");
                Printer.println("2-ая функция: x^3 - 0.77*x^2 - 1.251*x + 0.43 = 0");
                Printer.println("3-ая функция: sin(x) + 0.5 = 0");
                Printer.println("Введите номер функции (1-3):");
            }

            int n = Integer.parseInt(Reader.readLine());
            Func f = new Func(n);
            Graphic.setValues(f);
            Graphic.drawFunction();

            if (!Reader.isFile) Printer.println("Введите a:");
            double a = Double.parseDouble(Reader.readLine());
            if (!Reader.isFile) Printer.println("Введите b:");
            double b = Double.parseDouble(Reader.readLine());
            if (!Reader.isFile) Printer.println("Введите e:");
            double e = Double.parseDouble(Reader.readLine());
            Data data = new Data(a, b, e);

            if (!Reader.isFile) {
                Printer.println("Выберите метод решения:");
                Printer.println("3 - Метод Ньютона:");
                Printer.println("5 - Метод простых итерация");
            }
            int l = Integer.parseInt(Reader.readLine());
            f.solveType = l;

            switch (l) {
                case 5 -> {
                    Method5 m = new Method5(data, f);
                    m.start();
                }
                case 3 -> {
                    Method3 m = new Method3(data, f);
                    m.start();
                }
                default -> {
                }
            }
        } else {

            if (!Reader.isFile) {
                Printer.println("Система:");
                Printer.println("   Г f1(x1, x2) = -0.1(x1)^2 + 0.2(x2)^2 - 0.3 = 0");
                Printer.println("   L f2(x1, x2) = 0.2(x1)^2 + 0.1(x1) - 0.7 = 0");
            }
            FuncSystem fS = new FuncSystem(1);
            Graphic.setValues(fS);
            Graphic.drawSystemOfFunctions();

//            if (!Reader.isFile) Printer.println("Введите начальное приближение x1:");
//            double x1Start = Double.parseDouble(Reader.readLine());
//            if (!Reader.isFile) Printer.println("Введите начальное приближение x2:");
//            double x2Start = Double.parseDouble(Reader.readLine());
            if (!Reader.isFile) Printer.println("В каком диапазоне находится x1:");
            if (!Reader.isFile) Printer.println("a:");
            double x1a = Double.parseDouble(Reader.readLine());
            if (!Reader.isFile) Printer.println("b:");
            double x1b = Double.parseDouble(Reader.readLine());

            if (!Reader.isFile) Printer.println("В каком диапазоне находится x2:");
            if (!Reader.isFile) Printer.println("a:");
            double x2a = Double.parseDouble(Reader.readLine());
            if (!Reader.isFile) Printer.println("b:");
            double x2b = Double.parseDouble(Reader.readLine());

            if (!Reader.isFile) Printer.println("Введите e:");
            double e = Double.parseDouble(Reader.readLine());
            Data data = new Data(e, x1a, x1b, x2a, x2b);
            Method7 method7 = new Method7(data, fS);
            method7.start();

        }


    }
}
