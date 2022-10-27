package com.company.methods;

import com.company.Data;
import com.company.workers.Printer;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ExpFunc extends LinearFunc {

    public ExpFunc(Data data) {
        super(data);
    }

    @Override
    public void run() {
        try {
            int n = data.n;
            BigDecimal[] newYs = new BigDecimal[n + 1];
            for (int i = 1; i <= data.n; i++) {
                newYs[i] = BigDecimal.valueOf(Math.log(data.ys[i].doubleValue()));
            }
            Data newData = new Data(n, data.xs, newYs);

            LinearFunc firstDegFunc = new LinearFunc(newData);
            firstDegFunc.run();

            BigDecimal A = firstDegFunc.a;
            BigDecimal B = firstDegFunc.b;

            this.a = BigDecimal.valueOf(Math.pow(Math.E, B.doubleValue()));
            this.b = A;
            this.sig = getSigma();
            this.s = getS();
        } catch (NumberFormatException e){
            Printer.printErr("Невозможно найти функцию вида y = a * e^(bx)");
        }
    }



    @Override
    public void show() {
        Printer.println("y = " + a.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " * e^(" + b.round(new MathContext(10,RoundingMode.HALF_UP)).stripTrailingZeros() +" * x)");
    }

    @Override
    public BigDecimal fi(BigDecimal x) {
        return a.multiply(BigDecimal.valueOf(Math.pow(Math.E, b.multiply(x).doubleValue())));
    }

}
