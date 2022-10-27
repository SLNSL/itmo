package com.company.methods;

import com.company.Data;
import com.company.workers.Printer;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class PowerFunc extends LinearFunc {

    public PowerFunc(Data data){
        super(data);
    }

    public void run(){
        try {
            int n = data.n;
            BigDecimal[] newXs = new BigDecimal[n + 1];
            BigDecimal[] newYs = new BigDecimal[n + 1];
            for (int i = 1; i <= data.n; i++) {
                newXs[i] = BigDecimal.valueOf(Math.log(data.xs[i].doubleValue()));
                newYs[i] = BigDecimal.valueOf(Math.log(data.ys[i].doubleValue()));

            }

            Data newData = new Data(n, newXs, newYs);

            LinearFunc firstDegFunc = new LinearFunc(newData);
            firstDegFunc.run();

            BigDecimal A = firstDegFunc.a;
            BigDecimal B = firstDegFunc.b;
            this.b = A;
            this.a = BigDecimal.valueOf(Math.pow(Math.E, B.doubleValue()));
            this.sig = getSigma();
            this.s = getS();
        } catch (NumberFormatException e){
            Printer.printErr("Невозможно найти функцию вида y = ax^b");
        }

    }

    @Override
    public void show() {
        Printer.println("y = " + a.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " * x ^ (" + b.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + ")");
    }

    @Override
    public BigDecimal fi(BigDecimal x){
//        return a * Math.pow(x, b);
        return a.multiply(BigDecimal.valueOf(Math.pow(x.doubleValue(),b.doubleValue())));
    }

}
