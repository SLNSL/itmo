package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Никита\\Desktop\\Уник\\4 сем\\выч мат\\5 Lagrange Newtone Nikita\\input.txt");
        Scanner fileScanner = new Scanner(file);
        Scanner consoleScanner  = new Scanner(System.in);



        System.out.println("1: данные в виде таблицы из файла");
        System.out.println("2: данные в виде функции");

        Data data;
        double x0;
        if (consoleScanner.nextInt() == 2){
            System.out.println("1: y = sin(x)");
            System.out.println("2: y = cos(x)");
            System.out.println("3: y = sqrt(x)");
            int nOfFunc = consoleScanner.nextInt();

            System.out.println("Введите левую границу [x1; xn]");
            double x1 = consoleScanner.nextDouble();
            System.out.println("Введите правую границу [x1; xn]");
            double xn = consoleScanner.nextDouble();
            System.out.println("Введите n - количество точек на диапазоне [x1; xn]");
            int n = consoleScanner.nextInt();
            System.out.println("Введите x, для которого нужно найти приблеженное значение (например 2,37):");
            x0 = consoleScanner.nextDouble();
            data = Functions.makeValues(nOfFunc, x0, x1, xn, n);

        } else {
            int n = fileScanner.nextInt();
            double[] x = new double[n];
            double[] y = new double[n];

            for (int i = 0; i < n; i++){
                x[i] = fileScanner.nextDouble();
            }

            for (int i = 0; i < n; i++){
                y[i] = fileScanner.nextDouble();
            }
            System.out.println("Введите x, для которого нужно найти приблеженное значение (например 2,37):");
            x0 = consoleScanner.nextDouble();
            data = new Data(x0, x, y);


        }

        Table.printXYTable(data);
        if (x0 > data.x[data.n - 1] || x0 < data.x[0]) {
            System.out.println("Введённый x не входит в диапазон");
            System.exit(0);
        }
        System.out.println("Метод Лагранжа:");
        Lagrange lagrange = new Lagrange(data);
        lagrange.run();

        System.out.println();
        System.out.println("Метод Ньютона с конечными разностями:");
        Newton newton = new Newton(data);
        newton.run();

        Graphic graphic = new Graphic(newton, data, lagrange);
        graphic.draw();


//        Data data = new Data(0.35, x, y);
//        Newton newton = new Newton(data);
//        Table.printXYTable(data);
//        newton.run();
    }
}
