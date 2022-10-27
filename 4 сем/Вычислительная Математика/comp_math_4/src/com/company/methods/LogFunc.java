package com.company.methods;

import com.company.Data;
import com.company.workers.Printer;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class LogFunc extends LinearFunc {

    public LogFunc(Data data) {
        super(data);
    }

    @Override
    public void run() {
        try {
            int n = data.n;
            BigDecimal[] newXs = new BigDecimal[n + 1];
            for (int i = 1; i <= data.n; i++) {
                newXs[i] = BigDecimal.valueOf(Math.log(data.xs[i].doubleValue()));
            }
            Data newData = new Data(n, newXs, data.ys);


            LinearFunc firstDegFunc = new LinearFunc(newData);
            firstDegFunc.run();

            a = firstDegFunc.a;
            b = firstDegFunc.b;
            sig = getSigma();
            this.s = getS();
        } catch (NumberFormatException e){
            Printer.printErr("Невозможно найти функцию вида y = a*lnx + b");
        }
    }




    @Override
    public void show() {
        Printer.println("y = " + a.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " * ln(x) + " + b.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros());
    }

    @Override
    public BigDecimal fi(BigDecimal x) {
        if (x.doubleValue() == 0) x = x.add(BigDecimal.valueOf(0.000000000000000001));
        return a.multiply(BigDecimal.valueOf(Math.log(x.doubleValue()))).add(b);
    }




}
