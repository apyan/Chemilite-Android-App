package com.spectrus.chemport;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.spectrus.chemport.AppData.ActivityData;
import com.spectrus.chemport.AppFunction.AppGraphics;
import com.spectrus.chemport.AppFunction.AppStorage;

public class MenuFragmentScreen extends FragmentActivity {

    // Variables
    AppGraphics fragMGraphics;
    //AppStorage fileReader;
    public Context context;
    Button button_00, button_01, button_02, button_03;
    public ViewGroup.LayoutParams param_00;

    public long buttonClickTime = 0;
    public long TIME_THRESHOLD = 250;

    // Activity Fragment Variables
    ActivityData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fragment_screen);

        // Set up graphic variables on the screen
        context = MenuFragmentScreen.this;
        fragMGraphics = new AppGraphics(context);
        gameData = new ActivityData();
        //fileReader = new AppStorage(context, "user-cp-s.cp");

        button_00 = (Button)findViewById(R.id.button_00);
        button_01 = (Button)findViewById(R.id.button_01);
        button_02 = (Button)findViewById(R.id.button_02);
        button_03 = (Button)findViewById(R.id.button_03);

        // Fragment set-up
        //Fragment fragment_show = new Fragment();
        //View fragmentView = fragment_show.getView();
        //View fragmentView = findViewById(R.id.fragment_place);

        String fontPath = "fonts/Titillium-Bold.ttf";
        //String fontPath = "fonts/Sansation-Bold.ttf";
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);

        // Resizing buttons
        int widthDivision = fragMGraphics.getFullWidth() / 4;

        // Button 0
        param_00 = button_00.getLayoutParams();
        param_00.width = widthDivision;
        button_00.setLayoutParams(param_00);
        button_00.setTypeface(font);
        button_00.setTextColor(Color.WHITE);
        // Button 1
        param_00 = button_01.getLayoutParams();
        param_00.width = widthDivision;
        button_01.setLayoutParams(param_00);
        button_01.setTypeface(font);
        button_01.setTextColor(Color.WHITE);
        // Button 2
        param_00 = button_02.getLayoutParams();
        param_00.width = widthDivision;
        button_02.setLayoutParams(param_00);
        button_02.setTypeface(font);
        button_02.setTextColor(Color.WHITE);
        // Button 3
        param_00 = button_03.getLayoutParams();
        param_00.width = widthDivision;
        button_03.setLayoutParams(param_00);
        button_03.setTypeface(font);
        button_03.setTextColor(Color.WHITE);

        // Lesson Fragment as Default
        getFragmentManager().beginTransaction().add(R.id.fragment_place, new LessonFragment()).commit();

        //fileReader.dataRead();
        //fileReader.dataScan(); // Tester
    }

    // Lesson Fragment
    public void onLessons(View v){
        // Prevent multi-clicking, threshold of 250 ms
        if (SystemClock.elapsedRealtime() - buttonClickTime < TIME_THRESHOLD){
            return;
        }
        buttonClickTime = SystemClock.elapsedRealtime();

        getFragmentManager().beginTransaction().replace(R.id.fragment_place, new LessonFragment()).commit();
    }

    // Activities Fragment
    public void onActivities(View v){
        // Prevent multi-clicking, threshold of 250 ms
        if (SystemClock.elapsedRealtime() - buttonClickTime < TIME_THRESHOLD){
            return;
        }
        buttonClickTime = SystemClock.elapsedRealtime();

        getFragmentManager().beginTransaction().replace(R.id.fragment_place, new ActivityFragment()).commit();
    }

    // Index Fragment
    public void onIndex(View v){
        // Prevent multi-clicking, threshold of 250 ms
        if (SystemClock.elapsedRealtime() - buttonClickTime < TIME_THRESHOLD){
            return;
        }
        buttonClickTime = SystemClock.elapsedRealtime();

        getFragmentManager().beginTransaction().replace(R.id.fragment_place, new IndexFragment()).commit();
    }

    // Options Fragment
    public void onOptions(View v){
        // Prevent multi-clicking, threshold of 250 ms
        if (SystemClock.elapsedRealtime() - buttonClickTime < TIME_THRESHOLD){
            return;
        }
        buttonClickTime = SystemClock.elapsedRealtime();

        getFragmentManager().beginTransaction().replace(R.id.fragment_place, new OptionFragment()).commit();
    }

    // The phone's hardware back button is pressed
    @Override
    public void onBackPressed() {
        AppStorage file2 = new AppStorage(context, "user-cp-s.cp");
        file2.dataRead();
        if ((file2.dataRead[6].charAt(1) == '1')) {
            //this.finish();
            //System.exit(0);
            this.finishAffinity();
        } else {
            finish();
        }
    }
}
