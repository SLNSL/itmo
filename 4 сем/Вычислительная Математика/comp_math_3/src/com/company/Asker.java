package com.company;

public class Asker {

    public static int askNumberOfFunction(){
        Printer.println("1) ∫x²dx");
        Printer.println("2) ∫(x⁵ + 0.2x)dx");
        Printer.println("3) ∫(2x³ - 3x² + 5x - 9)dx ");
        Printer.println("4) ∫(10x⁸)dx");
        Printer.println("Введите номер функции: ");
        return Reader.readInt();
    }

    public static int askA(){
        Printer.println("Введите a:");
        return Reader.readInt();
    }

    public static int askB(){
        Printer.println("Введите b:");
        return Reader.readInt();
    }

    public static double askE(){
        Printer.println("Введите e:");
        String str = Reader.readString();
        return Double.parseDouble(str);
    }

    public static int askN(){
        Printer.println("Введите n:");
        return Reader.readInt();
    }

    public static int askMethod(){
        Printer.println("1: Метод Симпсона");
        Printer.println("2: Метод прямоугольников");
        Printer.println("Введите номер метода:");
        return Reader.readInt();
    }
}
