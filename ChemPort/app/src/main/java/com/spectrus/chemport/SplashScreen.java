package com.spectrus.chemport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.spectrus.chemport.AppFunction.AppGraphics;
import com.spectrus.chemport.AppFunction.AppStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.android.gms.ads.MobileAds;

public class SplashScreen extends Activity {

    // Variables for the Splash Screen activity
    public Context context;
    Animation anim = new AlphaAnimation(0.0f, 1.0f);
    AppStorage file, file1;
    PopupWindow closePopup, newUser;
    AppGraphics splashGraphics;
    TextView proceedS, appVersion, titleLogo, text_0, text_1, text_2, text_3, text_4;
    Button button_00;
    EditText editText_00;

    // Variables for font resources
    public String fontPath, fontPath_1, fontPath_2;
    public Typeface font, font_1, font_2;

    // Variables for AbMob advertising
    public AdView mAdView;
    public AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Set up graphic variables on the screen
        context = SplashScreen.this;
        splashGraphics = new AppGraphics(context);

        // Initialize AdMob Ads
        MobileAds.initialize(getApplicationContext(), getString(R.string.app_ad_id));
        mAdView = (AdView) findViewById(R.id.baseAd);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Create file
        file = new AppStorage(context, getString(R.string.user_data_file));
        file.doFileExist();
        file.dataRead();
        file.updateLastSessionDate();

        // If user chooses not to view the title screen
        if(file.dataRead[6].charAt(0) == '1') {
            startActivity(new Intent(this, MenuFragmentScreen.class));
            file1 = new AppStorage(context, getString(R.string.user_data_file));
            file1.dataRead();
            if(file1.dataRead[6].charAt(1) == '1'){
                this.finish();
                System.exit(0);
            }
        }

        // Set up the font resources
        fontPath = "fonts/Rubik-Regular.ttf";
        fontPath_1 = "fonts/Titillium-Bold.ttf";
        fontPath_2 = "fonts/Sansation-Light.ttf";
        font = Typeface.createFromAsset(this.getAssets(), fontPath);
        font_1 = Typeface.createFromAsset(this.getAssets(), fontPath_1);
        font_2 = Typeface.createFromAsset(this.getAssets(), fontPath_2);

        // Set Up Splash Prompt (Blinking)
        // You can manage the time of the blink with this parameter
        //titleLogo = (TextView) findViewById(R.id.titleLogo);
        proceedS = (TextView) findViewById(R.id.proceedString);
        appVersion = (TextView) findViewById(R.id.appVersion);
        //titleLogo.setTypeface(font_2);
        appVersion.setTypeface(font);
        proceedS.setTypeface(font);
        anim.setDuration(500);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        proceedS.startAnimation(anim);
    }

    // Proceeds to the Main Menu after any touch gesture
    public void onSplash(View v) {
        // Opens the User's data
        file = new AppStorage(context, getString(R.string.user_data_file));
        file.dataRead();

        // New Username Popup
        if(file.dataRead[0].length() < 5){
            LayoutInflater inflater_0 = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout_0 = inflater_0.inflate(R.layout.popup_main_000,
                    (ViewGroup) v.findViewById(R.id.popup_main_000));
            newUser = new PopupWindow(layout_0, splashGraphics.getFullWidth(),
                    splashGraphics.getFullHeight(), true);
            newUser.showAtLocation(layout_0, Gravity.CENTER, 0, 0);
            editText_00 = (EditText) newUser.getContentView().findViewById(R.id.editText_00);
            text_0 = (TextView) newUser.getContentView().findViewById(R.id.screen_title);
            text_1 = (TextView) newUser.getContentView().findViewById(R.id.text_00);
            text_2 = (TextView) newUser.getContentView().findViewById(R.id.text_01);
            text_3 = (TextView) newUser.getContentView().findViewById(R.id.text_02);
            text_4 = (TextView) newUser.getContentView().findViewById(R.id.text_03);
            text_0.setTypeface(font_1);
            text_1.setTypeface(font);
            text_2.setTypeface(font);
            text_3.setTypeface(font);
            text_4.setTypeface(font);
            button_00 = (Button) newUser.getContentView().findViewById(R.id.button_proceed);
            button_00.setTypeface(font);
            splashGraphics.dimPopUpBackground(newUser);
        } else {
            // Or goes to the next activity if user data exists
            startActivity(new Intent(this, MenuFragmentScreen.class));
        }
    }

    // Proceed Button in the new Username Popup
    public void onProceedName(View v){
        String newName = editText_00.getText().toString();
        if(newName.trim().length() < 5){
            text_4.setText(R.string.username_003b);
        } else {
            file.dataRead[0] = newName;
            file.dataWrite();
            newUser.dismiss();
        }
    }

    @Override
    public void onPause() {
        if(mAdView != null) mAdView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdView != null) mAdView.resume();
    }

    @Override
    public void onDestroy() {
        if(mAdView != null) mAdView.destroy();
        super.onDestroy();
    }

    // The phone's hardware back button is pressed
    @Override
    public void onBackPressed() {
        // End the application
        this.finish();
        System.exit(0);
    }
}
