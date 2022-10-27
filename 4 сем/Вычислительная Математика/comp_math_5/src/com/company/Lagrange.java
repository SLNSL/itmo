package com.company;

public class Lagrange {
    public Data data;


    public Lagrange(Data data){
        this.data = data;
    }

    public double getY(double x0){
        int n = data.n;
        double[] x = data.x;
        double[] y = data.y;
        double xF = x0;

        double l = 1;
        double L = 0;
        for (int i = 0; i < n; i++){

            for (int j = 0; j < n; j++){

                if (i != j) {
                   l *= (xF - x[j]) / (x[i] - x[j]);

//                    System.out.print("(x - x" + j+")");
//                    System.out.println(", (x" + i + " - x"+j+")");
                }
            }
//            System.out.printf("l%s = %f\n", i, (lUp/lDown) * y[i]);
//            System.out.println("l" + i + " = " + (lUp/lDown) * y[i]);
            l = l * y[i];
            L += l;
            l = 1;

//            System.out.println(L);


        }
        return L;
    }

    public void run(){
        System.out.println();
        System.out.println("L" + (data.n - 1) + " = " + getY(data.x0));
    }
}
