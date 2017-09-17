package com.spectrus.chemport.AppFunction;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

/**
 * Function for Testing and Debugging
 */
public class AppTesting {

    // Variables for Testing
    Context context;

    // Constructor
    public AppTesting(Context eContext) {
        context = eContext;
        Resources res = context.getResources();
    }

    // Toast Message for Integers
    public void intToastTest(int input){
        String output = input + "";
        Toast.makeText(context, "TEST: " + output, Toast.LENGTH_LONG).show();
    }

    // Toast Message for Boolean Array
    public void booleanArrayToastTest(boolean [] input){
        String output = "";
        for(int i = 0; i < input.length; i++){
            output = output + input[i] + " ";
            if((i % 4 == 0) && (i > 0)) {
                output += "\n";
            }
        }
        Toast.makeText(context, "TEST: " + output, Toast.LENGTH_LONG).show();
    }

    // Toast Message for String Array
    public void stringArrayToastTest(String [] input){
        String output = "";
        for(int i = 0; i < input.length; i++){
            output = output + input[i] + " ";
            if((i % 4 == 0) && (i > 0)) {
                output += "\n";
            }
        }
        Toast.makeText(context, "TEST: " + output, Toast.LENGTH_LONG).show();
    }

}
