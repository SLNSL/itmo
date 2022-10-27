package com.company;

import java.util.Scanner;

public class Reader {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(){
        return Integer.parseInt(scanner.nextLine());
    }

    public static double readDouble(){
        return Double.parseDouble(scanner.nextLine());
    }

    public static String readString(){
        return scanner.nextLine();
    }


}
