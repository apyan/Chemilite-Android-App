package com.spectrus.chemport.AppFunction;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.spectrus.chemport.R;

import java.util.Random;

/**
 * Functions used for the loading screen
 */
public class AppLoading {

    // Randomized Loading Quotes
    String [] loadingMessages;

    // Variables
    Context context;

    // Constructor
    public AppLoading(Context eContext){
        context = eContext;

        // Load Messages
        Resources res = context.getResources();
        loadingMessages = res.getStringArray(R.array.loading_messages);
    }

    // Randomize a Message
    public String randomMessage(){
        Random rand = new Random();
        int randNum = rand.nextInt(loadingMessages.length);
        String messageR = loadingMessages[randNum];
        return messageR;
    }

    // Shortened the Thread Sleeping Lines
    public void threadSleepUpdate(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    // Check Internet Connectivity
    public boolean connectivityExist(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}
