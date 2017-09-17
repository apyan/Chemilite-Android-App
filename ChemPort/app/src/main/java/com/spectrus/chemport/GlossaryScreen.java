package com.spectrus.chemport;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class GlossaryScreen extends Activity {

    // Variables
    public boolean abcMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary_screen);
    }

    // Glossary Help Popup
    public void onGlossaryHelp(View v){

    }

    // Sets to Alphabetical Mode
    public void onAlphabeticalMode(View v){

    }

    // Exits out of the Reader
    public void onExitButton(View v){
        // Exits activity
        finish();
    }
}
