package com.spectrus.chemport;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.spectrus.chemport.AppFunction.AppGraphics;

public class MultipleChoiceScreen extends Activity {

    // Variables for the MC Activity
    PopupWindow pausePopup;
    AppGraphics mcGraphics;
    public boolean popUpVisible;
    public boolean quitResume;

    Chronometer mcTimer;
    Context context;

    public String fontPath, fontPath_0, fontPath_1;
    public Typeface font, font_0, font_1;
    public Button button_00, button_01;
    public TextView text_00, text_01, text_02, text_03;

    public String [] mcQuestions;
    public String [][] mcAnswers;
    public boolean [][] mcCorrect;
    public int [] userInput;

    public int timerMode;
    public long pausedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_screen);
        mcGraphics = new AppGraphics(this);
        context = this;
        pausedTime = 0;

        // For Pause Popup maintainer
        popUpVisible = false;
        quitResume = false;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            timerMode = extras.getInt("timerMode");
        }

        // Set font resources
        fontPath = "fonts/Titillium-Bold.ttf";
        fontPath_0 = "fonts/Titillium-Semibold.ttf";
        fontPath_1 = "fonts/Rubik-Regular.ttf";
        font = Typeface.createFromAsset(this.getAssets(), fontPath);
        font_0 = Typeface.createFromAsset(this.getAssets(), fontPath_0);
        font_1 = Typeface.createFromAsset(this.getAssets(), fontPath_1);

        // Initiate the Chronometer
        mcTimer = (Chronometer) this.findViewById(R.id.mc_timer);
        if(timerMode == 0) {
            mcTimer.setVisibility(View.INVISIBLE);
        }
        mcTimer.setTypeface(font_1);
        mcTimer.start();
    }

    // Choice 1 Selected
    public void onChoice_1(View v){
    }

    // Choice 2 Selected
    public void onChoice_2(View v){
    }

    // Choice 3 Selected
    public void onChoice_3(View v){
    }

    // Choice 4 Selected
    public void onChoice_4(View v){
    }

    // Exit Button
    public void onExitButton(View v){
        if(!popUpVisible) {
            // Pause the Timer
            pausedTime = mcTimer.getBase() - SystemClock.elapsedRealtime();
            mcTimer.stop();

            popUpVisible = true;
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_pause_000,
                    (ViewGroup) v.findViewById(R.id.popup_pause_000));
            pausePopup = new PopupWindow(layout, mcGraphics.getFullWidth(),
                    mcGraphics.getFullHeight(), true);
            pausePopup.showAtLocation(layout, Gravity.CENTER, 0, 0);
            text_00 = (TextView) pausePopup.getContentView().findViewById(R.id.screen_title);
            text_01 = (TextView) pausePopup.getContentView().findViewById(R.id.text_00);
            text_02 = (TextView) pausePopup.getContentView().findViewById(R.id.text_01);
            text_03 = (TextView) pausePopup.getContentView().findViewById(R.id.text_02);
            button_00 = (Button) pausePopup.getContentView().findViewById(R.id.button_resume);
            button_01 = (Button) pausePopup.getContentView().findViewById(R.id.button_exit);
            text_00.setTypeface(font);
            text_01.setTypeface(font_1);
            text_02.setTypeface(font_1);
            text_03.setTypeface(font_1);
            button_00.setTypeface(font_1);
            button_01.setTypeface(font_1);
            mcGraphics.dimPopUpBackground(pausePopup);
        }
    }

    // Resume Button on Pause Popup
    public void onResumePause(View v){
        pausePopup.dismiss();
        popUpVisible = false;

        // Start back the Timer
        mcTimer.setBase(SystemClock.elapsedRealtime() + pausedTime);
        mcTimer.start();
    }

    // Exit Button on Pause Popup
    public void onExitPause(View v){
        pausePopup.dismiss();
        popUpVisible = false;
        quitResume = true;
        finish();
    }

    // The phone's hardware back button is pressed
    @Override
    public void onBackPressed() {
        if(!popUpVisible) {
            // Pause the Timer
            pausedTime = mcTimer.getBase() - SystemClock.elapsedRealtime();
            mcTimer.stop();

            popUpVisible = true;
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_pause_000,
                    (ViewGroup) this.findViewById(R.id.popup_pause_000));
            pausePopup = new PopupWindow(layout, mcGraphics.getFullWidth(),
                    mcGraphics.getFullHeight(), true);
            pausePopup.showAtLocation(layout, Gravity.CENTER, 0, 0);
            text_00 = (TextView) pausePopup.getContentView().findViewById(R.id.screen_title);
            text_01 = (TextView) pausePopup.getContentView().findViewById(R.id.text_00);
            text_02 = (TextView) pausePopup.getContentView().findViewById(R.id.text_01);
            text_03 = (TextView) pausePopup.getContentView().findViewById(R.id.text_02);
            button_00 = (Button) pausePopup.getContentView().findViewById(R.id.button_resume);
            button_01 = (Button) pausePopup.getContentView().findViewById(R.id.button_exit);
            text_00.setTypeface(font);
            text_01.setTypeface(font_1);
            text_02.setTypeface(font_1);
            text_03.setTypeface(font_1);
            button_00.setTypeface(font_1);
            button_01.setTypeface(font_1);
            mcGraphics.dimPopUpBackground(pausePopup);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(!quitResume) {
            if (!popUpVisible) {
                // Pause the Timer
                pausedTime = mcTimer.getBase() - SystemClock.elapsedRealtime();
                mcTimer.stop();

                popUpVisible = true;
                LayoutInflater inflater = (LayoutInflater) context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_pause_000,
                        (ViewGroup) this.findViewById(R.id.popup_pause_000));
                pausePopup = new PopupWindow(layout, mcGraphics.getFullWidth(),
                        mcGraphics.getFullHeight(), true);
                pausePopup.showAtLocation(layout, Gravity.CENTER, 0, 0);
                text_00 = (TextView) pausePopup.getContentView().findViewById(R.id.screen_title);
                text_01 = (TextView) pausePopup.getContentView().findViewById(R.id.text_00);
                text_02 = (TextView) pausePopup.getContentView().findViewById(R.id.text_01);
                text_03 = (TextView) pausePopup.getContentView().findViewById(R.id.text_02);
                button_00 = (Button) pausePopup.getContentView().findViewById(R.id.button_resume);
                button_01 = (Button) pausePopup.getContentView().findViewById(R.id.button_exit);
                text_00.setTypeface(font);
                text_01.setTypeface(font_1);
                text_02.setTypeface(font_1);
                text_03.setTypeface(font_1);
                button_00.setTypeface(font_1);
                button_01.setTypeface(font_1);
                mcGraphics.dimPopUpBackground(pausePopup);
            }
        }
    }
}
