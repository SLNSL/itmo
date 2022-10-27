package com.company.methods;

import com.company.Func;

public abstract class Method implements Runnable{

    public static Method createMethod(int numberOfMethod, int n, double e, Func f){
        return switch (numberOfMethod){
            case 1 -> new Simpson(n, e, f);
            case 2 -> new Rectangle(n, e, f);
            default -> null;
        };
    }

}
