package com.company.workers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
    public static boolean isFile;

    private static final Scanner scanner = new Scanner(System.in);

    private static final File file = new File("C:\\Users\\Никита\\Desktop\\Уник\\4 сем\\выч мат\\4lab\\input.in");
    private static FileReader fr;
    private static BufferedReader bf;

    static {
        try {
            fr = new FileReader(file);
            bf = new BufferedReader(fr);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static String readLine(){
        String string = null;

        while (string == null || string.equals("")) {
            try {
                if (!isFile) {
                    string = scanner.nextLine();
                } else {
                    string = bf.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return string;
    }
}
