package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileReader("input.txt"));
            int n = 20;
            double[] a = new double[n];
            for (int i = 0; i < n; i++){
                a[i] =  Double.parseDouble(scanner.nextLine());
            }
            Func.a = a;
            Func.getVars();
            Func.getExtremeValues();
            Func.getSampleSize();
            Func.getMX();
            Func.getDX();
            Func.getSig();
            Func.getVectors();
            Func.getEmpiricFunc();
            Func.getStaticPol();




        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
