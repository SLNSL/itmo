package com.company;

import java.math.BigDecimal;

public class Data {

    public BigDecimal[] xs;
    public BigDecimal[] ys;
    public int n;

    public Data(int n, BigDecimal[] xs, BigDecimal[] ys){
        this.n = n;
        this.xs = xs;
        this.ys = ys;
    }
}
