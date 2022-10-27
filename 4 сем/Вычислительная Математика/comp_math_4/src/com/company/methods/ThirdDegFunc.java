package com.company.methods;

import com.company.Data;
import com.company.workers.Matrix4x4Solver;
import com.company.workers.Printer;
import com.company.wrapper.FourAnswers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ThirdDegFunc extends SecDegFunc{

    public BigDecimal a0;
    public BigDecimal a1;
    public BigDecimal a2;
    public BigDecimal a3;

    public ThirdDegFunc(Data data) {
        super(data);
    }

    @Override
    public void run() {
        FourAnswers fa = Matrix4x4Solver.calculateForTwo(data.n, getSX(), getSX2(), getSX3(),
                getSX4(), getSX5(), getSX6(), getSY(), getSXY(), getSX2Y(), getSX3Y());

        a0 = fa.a0;
        a1 = fa.a1;
        a2 = fa.a2;
        a3 = fa.a3;
        sig = getSigma();
        this.s = getS();

    }

    @Override
    public void show() {
        Printer.println("y = " + a3.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " * x^3 + " + a2.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() +
                " * x^2 + " + a1.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros() + " * x + "+ a0.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros());
    }

    @Override
    public BigDecimal fi(BigDecimal x) {
        return a0.add(a1.multiply(x)).add(a2.multiply(x).multiply(x)).add(a3.multiply(x).multiply(x).multiply(x));
    }

    @Override
    public BigDecimal getSX() {
        return super.getSX();
    }

    @Override
    public BigDecimal getSX2() {
        return super.getSX2();
    }

    @Override
    public BigDecimal getSY() {
        return super.getSY();
    }

    @Override
    public BigDecimal getSXY() {
        return super.getSXY();
    }

    @Override
    public BigDecimal getSX3() {
        return super.getSX3();
    }

    @Override
    public BigDecimal getSX4() {
        return super.getSX4();
    }

    @Override
    public BigDecimal getSX2Y() {
        return super.getSX2Y();
    }

    public BigDecimal getSX5(){
        BigDecimal s5 = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
//            s5 += data.xs[i] * data.xs[i] * data.xs[i] * data.xs[i] * data.xs[i];
            s5 = s5.add(data.xs[i].multiply(data.xs[i]).multiply(data.xs[i]).multiply(data.xs[i]).multiply(data.xs[i]));
        }
        return s5;
    }

    public BigDecimal getSX6(){
        BigDecimal s6 = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            s6 = s6.add(data.xs[i].multiply(data.xs[i]).multiply(data.xs[i]).multiply(data.xs[i]).multiply(data.xs[i]).multiply(data.xs[i]));
        }
        return s6;
    }

    public BigDecimal getSX3Y(){
        BigDecimal sx3y = new BigDecimal(0);
        for (int i = 1; i <= data.n; i++){
            sx3y = sx3y.add(data.ys[i].multiply(data.xs[i]).multiply(data.xs[i]).multiply(data.xs[i]));
        }
        return sx3y;
    }

    @Override
    public BigDecimal getSigma() {
        return super.getSigma();
    }
}
