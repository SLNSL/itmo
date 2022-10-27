package com.company;

import com.company.methods.*;
import com.company.methods.abstracts.Method;
import com.company.workers.Graphic;
import com.company.workers.Printer;

import java.math.BigDecimal;

public class BestFunction {


    public static Method getBestFunction(Data data){
        LinearFunc firstDegFunc = new LinearFunc(data);
        SecDegFunc secDegFunc = new SecDegFunc(data);
        ThirdDegFunc thirdDegFunc = new ThirdDegFunc(data);
        PowerFunc powerFunc = new PowerFunc(data);
        ExpFunc expFunc = new ExpFunc(data);
        LogFunc logFunc = new LogFunc(data);

        firstDegFunc.run();
        secDegFunc.run();
        thirdDegFunc.run();
        powerFunc.run();
        expFunc.run();
        logFunc.run();

        printTable(firstDegFunc, secDegFunc, thirdDegFunc, logFunc, expFunc, powerFunc);
        Graphic.drawFunctions(data, expFunc, firstDegFunc, logFunc, powerFunc, secDegFunc, thirdDegFunc);
        Printer.println("");


        BigDecimal minSig = firstDegFunc.sig;
        Method method = firstDegFunc;

        if (powerFunc.sig != null && powerFunc.sig.compareTo(minSig) < 0){
            minSig = powerFunc.sig;
            method = powerFunc;
        }


        if (expFunc.sig != null && expFunc.sig.compareTo(minSig) < 0){
            minSig = expFunc.sig;
            method = expFunc;
        }

        if (logFunc.sig != null && logFunc.sig.compareTo(minSig) < 0){
            minSig = logFunc.sig;
            method = logFunc;
        }

        if (secDegFunc.sig.compareTo(minSig) < 0){
            minSig = secDegFunc.sig;
            method = secDegFunc;
        }

        if (thirdDegFunc.sig.compareTo(minSig) < 0){
            minSig = thirdDegFunc.sig;
            method = thirdDegFunc;
        }






        return method;
    }

    public static void printTable(LinearFunc linearFunc,
                           SecDegFunc secDegFunc,
                           ThirdDegFunc thirdDegFunc,
                           LogFunc logFunc,
                           ExpFunc expFunc,
                           PowerFunc powerFunc){
        Printer.println("-----------------------------------------------------------------------------------------------------");
        Printer.println("|Вид функции                |a          |b          |c          |d          |S          |sigma      |");
        Printer.println("-----------------------------------------------------------------------------------------------------");
        Printer.printf( "|y = ax + b                 |%-11.6f|%-11.6f|-          |-          |%-11.6f|%-11.8f|\n",
                linearFunc.a, linearFunc.b, linearFunc.s, linearFunc.sig);
        Printer.println("-----------------------------------------------------------------------------------------------------");  
        Printer.printf( "|y = ax^2 + bx + c          |%-11.6f|%-11.6f|%-11.6f|-          |%-11.6f|%-11.8f|\n",
                secDegFunc.a2, secDegFunc.a1, secDegFunc.a0, secDegFunc.s, secDegFunc.sig);
        Printer.println("-----------------------------------------------------------------------------------------------------");    
        Printer.printf( "|y = ax^3 + bx^2 + cx + d   |%-11.6f|%-11.6f|%-11.6f|%-11.6f|%-11.6f|%-11.8f|\n",
                thirdDegFunc.a3, thirdDegFunc.a2, thirdDegFunc.a1, thirdDegFunc.a0, thirdDegFunc.s, thirdDegFunc.sig);
        Printer.println("-----------------------------------------------------------------------------------------------------");    
        Printer.printf( "|y = ax^b                   |%-11.6f|%-11.6f|-          |-          |%-11.6f|%-11.8f|\n",
                powerFunc.a, powerFunc.b, powerFunc.s, powerFunc.sig);
        Printer.println("-----------------------------------------------------------------------------------------------------");     
        Printer.printf( "|y = ae^(bx)                |%-11.6f|%-11.6f|-          |-          |%-11.6f|%-11.8f|\n",
                expFunc.a, expFunc.b, expFunc.s, expFunc.sig);
        Printer.println("-----------------------------------------------------------------------------------------------------");   
        Printer.printf( "|y = a*lnx + b              |%-11.6f|%-11.6f|-          |-          |%-11.6f|%-11.8f|\n",
                logFunc.a, logFunc.b, logFunc.s, logFunc.sig);
        Printer.println("-----------------------------------------------------------------------------------------------------");
    }
}
