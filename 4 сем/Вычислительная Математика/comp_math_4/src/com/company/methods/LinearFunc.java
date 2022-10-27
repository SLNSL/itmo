package com.company.methods;

import com.company.Data;
import com.company.methods.abstracts.Method;
import com.company.workers.Printer;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class LinearFunc extends Method {
    public BigDecimal a;
    public BigDecimal b;


    public LinearFunc(Data data){
        super(data);
    }

    public void run(){
        BigDecimal sx = getSX();
        BigDecimal sxx = getSX2();
        BigDecimal sy = getSY();
        BigDecimal sxy = getSXY();
        int n = data.n;

//        BigDecimal d = sxx * n - sx * sx;
        BigDecimal d = sxx.multiply(BigDecimal.valueOf(n)).subtract(sx.multiply(sx));


//        BigDecimal d1 = sxy * n - sx * sy;
        BigDecimal d1 = sxy.multiply(BigDecimal.valueOf(n)).subtract(sx.multiply(sy));

//        BigDecimal d2 = sxx * sy - sx * sxy;
        BigDecimal d2 = sxx.multiply(sy).subtract(sx.multiply(sxy));

        a = d1.divide(d, 10, RoundingMode.HALF_UP);
//        a = BigDecimal.valueOf(d1.doubleValue() / d.doubleValue());

        b = d2.divide(d, 10, RoundingMode.HALF_UP);
//        b = BigDecimal.valueOf(d2.doubleValue() / d.doubleValue());

        sig = getSigma();
        s = getS();
    }



    @Override
    public void show() {
        Printer.println("y = " + a.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " * x + " + b.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros());
        Printer.println("r = " + getCorrelation());
    }

    @Override
    public BigDecimal fi(BigDecimal x){
        return x.multiply(a).add(b);
    }

    public BigDecimal getSX(){
        BigDecimal sx = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            sx = sx.add(data.xs[i]);
        }
        return sx;
    }

    public BigDecimal getSX2(){
        BigDecimal sxx = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            sxx = sxx.add(data.xs[i].multiply(data.xs[i]));
        }
        return sxx;
    }

    public BigDecimal getSY(){
        BigDecimal sy = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            sy = sy.add(data.ys[i]);
        }
        return sy;
    }

    public BigDecimal getSXY(){
        BigDecimal sxy = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            sxy = sxy.add(data.ys[i].multiply(data.xs[i]));
        }
        return sxy;
    }

    public BigDecimal getCorrelation(){
        BigDecimal x_ = getSX().divide(BigDecimal.valueOf(data.n), 10, RoundingMode.HALF_UP);
        BigDecimal y_ = getSY().divide(BigDecimal.valueOf(data.n), 10, RoundingMode.HALF_UP);

        BigDecimal r1 = new BigDecimal(0), r2 = new BigDecimal(0), r3 = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            r1 = r1.add((data.xs[i].subtract(x_)).multiply(data.ys[i].subtract(y_)));

//            r2 += (data.xs[i] - x_)*(data.xs[i] - x_);
            r2 = r2.add((data.xs[i].subtract(x_)).multiply(data.xs[i].subtract(x_)));
            r3 = r3.add((data.ys[i].subtract(y_)).multiply(data.ys[i].subtract(y_)));
        }
        return r1.divide(BigDecimal.valueOf(Math.sqrt(r2.multiply(r3).doubleValue())), 10, RoundingMode.HALF_UP);

    }
}
