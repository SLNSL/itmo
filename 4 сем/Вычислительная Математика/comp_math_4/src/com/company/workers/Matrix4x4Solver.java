package com.company.workers;

import com.company.wrapper.FourAnswers;
import com.company.wrapper.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matrix4x4Solver {


    public static FourAnswers calculateForTwo(int n,
                                              BigDecimal sx, BigDecimal sx2, BigDecimal sx3,
                                              BigDecimal sx4, BigDecimal sx5, BigDecimal sx6,
                                              BigDecimal sy, BigDecimal sxy, BigDecimal sx2y, BigDecimal sx3y){
        BigDecimal[][] a = new BigDecimal[4][4];
        BigDecimal[] b = new BigDecimal[4];

        a[0][0] = BigDecimal.valueOf(n);
        a[0][1] = sx;
        a[0][2] = sx2;
        a[0][3] = sx3;

        a[1][0] = sx;
        a[1][1] = sx2;
        a[1][2] = sx3;
        a[1][3] = sx4;

        a[2][0] = sx2;
        a[2][1] = sx3;
        a[2][2] = sx4;
        a[2][3] = sx5;

        a[3][0] = sx3;
        a[3][1] = sx4;
        a[3][2] = sx5;
        a[3][3] = sx6;

        b[0] = sy;
        b[1] = sxy;
        b[2] = sx2y;
        b[3] = sx3y;

        int N  = 4;
        for (int p = 0; p < N; p++) {

            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(a[i][p].doubleValue()) > Math.abs(a[max][p].doubleValue())) {
                    max = i;
                }
            }
            BigDecimal[] temp = a[p]; a[p] = a[max]; a[max] = temp;
            BigDecimal   t    = b[p]; b[p] = b[max]; b[max] = t;


            for (int i = p + 1; i < N; i++) {
                BigDecimal alpha = a[i][p].divide(a[p][p], 10, RoundingMode.HALF_UP);
//                BigDecimal alpha = BigDecimal.valueOf(a[i][p].doubleValue() / a[p][p].doubleValue());

                b[i] = b[i].subtract(alpha.multiply(b[p]));
                for (int j = p; j < N; j++) {
                    a[i][j] = a[i][j].subtract(alpha.multiply(a[p][j]));
                }
            }
        }


       BigDecimal[] x = new BigDecimal[N];
        for (int i = N - 1; i >= 0; i--) {
            BigDecimal sum = new BigDecimal(0);
            for (int j = i + 1; j < N; j++) {
                sum = sum.add(a[i][j].multiply(x[j]));
            }
            x[i] = (b[i].subtract(sum)).divide(a[i][i], 10, RoundingMode.HALF_UP);
//            x[i] = BigDecimal.valueOf((b[i].subtract(sum)).doubleValue() / a[i][i].doubleValue());
        }

        return new FourAnswers(x[0], x[1], x[2], x[3]);
    }
}
