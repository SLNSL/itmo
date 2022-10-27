package com.company.methods;

import com.company.Data;
import com.company.workers.Printer;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SecDegFunc extends LinearFunc {
    private BigDecimal sx;
    private BigDecimal sx2;
    private BigDecimal sx3;
    private BigDecimal sx4;
    private BigDecimal sy;
    private BigDecimal sxy;
    private BigDecimal sx2y;
    private int n;

    public BigDecimal a0;
    public BigDecimal a1;
    public BigDecimal a2;



    public SecDegFunc(Data data){
        super(data);
    }

    public void run(){
        sx = getSX();
        sx2 = getSX2();
        sx3 = getSX3();
        sx4 = getSX4();
        sy = getSY();
        sxy = getSXY();
        sx2y = getSX2Y();
        n = data.n;

        a0 = getDeltaA0().divide(getDeltaA(), 10, RoundingMode.HALF_UP);
//        a0 = BigDecimal.valueOf(getDeltaA0().doubleValue() / getDeltaA().doubleValue());

        a1 = getDeltaA1().divide(getDeltaA(), 10, RoundingMode.HALF_UP);
//        a1 = BigDecimal.valueOf(getDeltaA1().doubleValue() / getDeltaA().doubleValue());

        a2 = getDeltaA2().divide(getDeltaA(), 10, RoundingMode.HALF_UP);
//        a2 = BigDecimal.valueOf(getDeltaA2().doubleValue() / getDeltaA().doubleValue());



        sig = getSigma();
        this.s = getS();

    }

    @Override
    public void show() {
        Printer.println("y = " + a2.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " * x^2 + " + a1.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " * x + " + a0.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros());
    }

    @Override
    public BigDecimal fi(BigDecimal x){
//        return a0 + a1 * x + a2 * x * x;
        return a0.add(a1.multiply(x)).add(a2.multiply(x).multiply(x));
    }

    public BigDecimal getDeltaA(){
//        return n * (sx2 * sx4 - sx3 * sx3) -
//                sx * (sx * sx4 - sx3 * sx2) +
//                sx2 * (sx * sx3 - sx2 * sx2);
        return BigDecimal.valueOf(n).multiply(sx2.multiply(sx4).subtract(sx3.multiply(sx3)))
                .subtract(sx.multiply(sx.multiply(sx4).subtract(sx3.multiply(sx2))))
                .add(sx2.multiply(sx.multiply(sx3).subtract(sx2.multiply(sx2))));
    }

    public BigDecimal getDeltaA0(){
//        return sy * (sx2 * sx4 - sx3 * sx3) -
//                sx * (sxy * sx4 - sx3 * sx2y) +
//                sx2 * (sxy * sx3 - sx2 * sx2y);
        return sy.multiply(sx2.multiply(sx4).subtract(sx3.multiply(sx3)))
                .subtract(sx.multiply(sxy.multiply(sx4).subtract(sx3.multiply(sx2y))))
                .add(sx2.multiply(sxy.multiply(sx3).subtract(sx2.multiply(sx2y))));
    }

    public BigDecimal getDeltaA1(){
//        return n * (sxy * sx4 - sx3 * sx2y) -
//                sy * (sx * sx4 - sx3 * sx2) +
//                sx2 * (sx * sx2y - sxy * sx2);
        return BigDecimal.valueOf(n).multiply(sxy.multiply(sx4).subtract(sx3.multiply(sx2y)))
                .subtract(sy.multiply(sx.multiply(sx4).subtract(sx3.multiply(sx2))))
                .add(sx2.multiply(sx.multiply(sx2y).subtract(sxy.multiply(sx2))));
    }

    public BigDecimal getDeltaA2(){
//        return n * (sx2 * sx2y - sxy * sx3) -
//                sx * (sx * sx2y - sxy * sx2) +
//                sy * (sx * sx3 - sx2 * sx2);
        return BigDecimal.valueOf(n).multiply(sx2.multiply(sx2y).subtract(sxy.multiply(sx3)))
                .subtract(sx.multiply(sx.multiply(sx2y).subtract(sxy.multiply(sx2))))
                .add(sy.multiply(sx.multiply(sx3).subtract(sx2.multiply(sx2))));
    }



    public BigDecimal getSX3(){
        BigDecimal sx3= new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            sx3 = sx3.add(data.xs[i].multiply(data.xs[i]).multiply(data.xs[i]));
        }
        return sx3;
    }

    public BigDecimal getSX4(){
        BigDecimal s4 = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            s4 = s4.add(data.xs[i].multiply(data.xs[i]).multiply(data.xs[i]).multiply(data.xs[i]));
        }
        return s4;
    }

    public BigDecimal getSX2Y(){
        BigDecimal sx2y = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            sx2y = sx2y.add(data.ys[i].multiply(data.xs[i]).multiply(data.xs[i]));
        }
        return sx2y;
    }


}
