package com.company.methods.abstracts;

import com.company.Data;
import com.company.methods.abstracts.HasFi;
import com.company.workers.Printer;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public abstract class Method implements HasFi, HasFormula, HasTable{
    public Data data;
    public BigDecimal sig;
    public BigDecimal s;




    public Method(Data data){
        this.data = data;
    }

    public BigDecimal getSigma(){
        BigDecimal sigma = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
//            System.out.println("!!!! " + fi(data.xs[i]).round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " "  + data.ys[i].round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros());
//            System.out.println("!!!!" + sigma);
//            sigma += (fi(data.xs[i]) - data.ys[i]) * (fi(data.xs[i]) - data.ys[i]);
            sigma = sigma.add((fi(data.xs[i]).subtract(data.ys[i])).multiply((fi(data.xs[i]).subtract(data.ys[i]))));
        }
//        sigma /= data.n;
//        sigma = sigma.divide(BigDecimal.valueOf(data.n),2, RoundingMode.HALF_UP);
        sigma = BigDecimal.valueOf(sigma.doubleValue() / data.n);

//        sigma = Math.sqrt(sigma);
        sigma = BigDecimal.valueOf(Math.sqrt(sigma.doubleValue()));
        return sigma;
    }

    public void printTable(){

        printLine();
        Printer.print("|         ");
        for (int i = 1; i <= data.n; i++){
            Printer.printf("|%-9d", i);
        }
        Printer.println("|");

        printLine();
        Printer.print("|    X    ");
        for (int i = 1; i <= data.n; i++){
            Printer.printf("|%-9.5f", data.xs[i]);
        }
        Printer.println("|");

        printLine();
        Printer.print("|    Y    ");
        for (int i = 1; i <= data.n; i++){
            Printer.printf("|%-9.5f", data.ys[i]);
        }
        Printer.println("|");

        printLine();
        Printer.print("|   Fi    ");
        for (int i = 1; i <= data.n; i++){
            Printer.printf("|%-9.5f", fi(data.xs[i]));
        }
        Printer.println("|");

        printLine();
        Printer.print("|    E    ");
        for (int i = 1; i <= data.n; i++){
            Printer.printf("|%-9.5f", fi(data.xs[i]).subtract(data.ys[i]));
        }
        Printer.println("|");
        printLine();

    }

    public void printLine(){
        for (int i = 1; i <= data.n + 1; i++){
            Printer.print("----------");
        }
        Printer.println("-");
    }

    public BigDecimal getS() {
        BigDecimal s = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            s = s.add(fi(data.xs[i]).subtract(data.ys[i]).multiply(fi(data.xs[i]).subtract(data.ys[i])));
        }
        return s;
    }


}
