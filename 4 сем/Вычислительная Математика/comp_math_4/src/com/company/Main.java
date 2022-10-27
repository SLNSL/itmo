package com.company;

import com.company.methods.LinearFunc;
import com.company.methods.SecDegFunc;
import com.company.methods.abstracts.Method;
//import com.company.workers.Graphic;
import com.company.workers.Printer;
import com.company.workers.Reader;

import java.math.BigDecimal;

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


        if (!Reader.isFile) {
            Printer.println("Введите значение n - количество точек:");
        }
        int n = Integer.parseInt(Reader.readLine());



        BigDecimal[] x = new BigDecimal[n + 1];
        BigDecimal[] y = new BigDecimal[n + 1];

        if (!Reader.isFile) {
            Printer.println("Введите значения x1 x2 x3 ... x" + n + " (через пробел)");
        }
        String xString = Reader.readLine();
        for (int i = 1; i <= n; i++){
            x[i] = BigDecimal.valueOf(Double.parseDouble(xString.split(" ")[i - 1]));
        }


        if (!Reader.isFile) {
            Printer.println("Введите значения y1 y2 y3 ... y" + n + " (через пробел)");
        }
        String yString = Reader.readLine();
        for (int i = 1; i <= n; i++){
            y[i] = BigDecimal.valueOf(Double.parseDouble(yString.split(" ")[i - 1]));
        }
        Printer.isFile = outIsFile;

        Data data = new Data(n, x, y);

        Method method = BestFunction.getBestFunction(data);
        method.show();
        Printer.println("sigma = " + method.getSigma());
        Printer.println("");
        method.printTable();










    }
}
