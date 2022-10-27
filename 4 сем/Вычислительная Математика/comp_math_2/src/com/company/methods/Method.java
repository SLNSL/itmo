package com.company.methods;

import com.company.Data;
import com.company.Func;
import com.company.FuncSystem;

public abstract class Method implements MethodINT{
    public Data data;
    public Func f;
    public FuncSystem fS;

    public Method(Data data, Func f){
        this.data = data;
        this.f = f;
    }

    public Method(Data data, FuncSystem fS){
        this.data = data;
        this.fS = fS;
    }

//    protected boolean check(double a, double b, Func f){
//        return true;
//    }
}
