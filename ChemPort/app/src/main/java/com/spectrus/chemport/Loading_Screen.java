package com.spectrus.chemport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.spectrus.chemport.AppData.ActivityData;
import com.spectrus.chemport.AppData.ElementData;
import com.spectrus.chemport.AppFunction.AppLoading;
import com.spectrus.chemport.AppFunction.AppTesting;

import java.util.Random;

public class Loading_Screen extends Activity {

    // Loading Delay Time
    private final int WAIT_TIME = 3000;
    private static final int PROGRESS = 0x1;
    public AppTesting testingFun;
    ImageView loadImg;

    public AppLoading loadingFun;
    public ActivityData activityData;
    public ElementData elementData;

    //private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
    private Context context;

    // Flashcard Data
    public int elementRange;
    public String [] chem_symbols;
    public String [] chem_elements;
    public boolean [] card_flipped;
    public int cardMaximum;

    // Multiple Choice Data
    public String [] mc_questions;
    public String [] [] mc_choices;
    public boolean [] [] answer_choices;

    Random randomizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);

        // Set Activity Data
        activityData = new ActivityData();
        elementData = new ElementData();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityData.activityValue = extras.getString("gameActivity");
            activityData.default_mode = extras.getBoolean("gameMode");
            activityData.default_level = extras.getInt("gameLevel");
            activityData.gameMode = extras.getIntArray("gamePhase");
        }

        context = this;
        loadingFun = new AppLoading(context);
        testingFun = new AppTesting(context);
        //mProgress = (ProgressBar) findViewById(R.id.progressBar);

        loadImg = (ImageView) findViewById(R.id.loadImg);

        String fontPath = "fonts/Rubik-Regular.ttf";
        Typeface rr = Typeface.createFromAsset(getAssets(), fontPath);
        TextView proceedS = (TextView) findViewById(R.id.loadingText);
        proceedS.setTypeface(rr);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        proceedS.startAnimation(animation);

        // Rotation Spin Image Animation
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(800);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setFillAfter(true); //
        loadImg.startAnimation(rotate);

        TextView proceedM = (TextView) findViewById(R.id.messageText);
        proceedM.setTypeface(rr);
        proceedM.setText(loadingFun.randomMessage());

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while(mProgressStatus < 100) {
                    // Do work (Progress Calculation)

                    // Check for Wi-Fi Connection
                    if(mProgressStatus == 0) {
                        boolean b = loadingFun.connectivityExist();

                        // Set-Up Activity Data
                        switch(activityData.activityValue) {
                            case "GLOSSARY":
                                break;
                            case "FLASHCARD_0":
                                // Initializes the flashcards
                                randomizer = new Random();
                                elementRange = activityData.cards_Ran[activityData.default_level];
                                // Data Transferred
                                chem_symbols = new String [activityData.cards_Num[activityData.default_level]];
                                chem_elements = new String [activityData.cards_Num[activityData.default_level]];
                                card_flipped = new boolean [activityData.cards_Num[activityData.default_level]];
                                cardMaximum = activityData.cards_Num[activityData.default_level];
                                loadFlashCards(chem_symbols, chem_elements, card_flipped, elementRange);
                                break;
                            case "MULTICHOICE_0":
                                // Initializes the multiple choice answers
                                randomizer = new Random();
                                // Data Transferred
                                // create boolean answer array (4)
                                // create string answer array (4)
                                // create string question array
                                mc_questions = new String [activityData.mc_Num_EleSym[activityData.default_level]];
                                mc_choices = new String [activityData.mc_Num_EleSym[activityData.default_level]][4];
                                answer_choices = new boolean [activityData.mc_Num_EleSym[activityData.default_level]][4];
                                break;
                            default:
                                break;
                        }

                        mProgressStatus += 100;
                    }

                    try {
                        // 1000 = 1 second
                        Thread.sleep(1250);
                        //mProgress.setProgress(mProgressStatus);
                    } catch (Exception e) {
                        e.getLocalizedMessage();
                    }

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            /*mProgress.setProgress(mProgressStatus);
                            try{ Thread.sleep(500); }catch(InterruptedException e){ }*/
                            boolean a = loadingFun.connectivityExist();
                            //Toast.makeText(context, "Connection: " + a, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                // Goes to Respective Screens
                switch(activityData.activityValue) {
                    case "GLOSSARY":
                        Intent d = new Intent(Loading_Screen.this, GlossaryScreen.class);
                        Loading_Screen.this.startActivity(d);
                        break;
                    case "FLASHCARD_0":
                        Intent d0 = new Intent(Loading_Screen.this, FlashcardScreen.class);
                        d0.putExtra("gameMode", activityData.default_mode);
                        d0.putExtra("chemSymbols", chem_symbols);
                        d0.putExtra("chemElements", chem_elements);
                        d0.putExtra("cardFlipped", card_flipped);
                        d0.putExtra("cardMax", cardMaximum);
                        Loading_Screen.this.startActivity(d0);
                        break;
                    case "MULTICHOICE_0":
                        Intent d1 = new Intent(Loading_Screen.this, MultipleChoiceScreen.class);
                        d1.putExtra("timerMode", activityData.gameMode[1]);
                        Loading_Screen.this.startActivity(d1);
                        break;
                    default:
                        // Demo Default
                        /*Intent xx = new Intent(Loading_Screen.this, demo_gameplay.class);
                        xx.putExtra("stageType", 0);
                        Loading_Screen.this.startActivity(xx);*/
                        break;
                }
                Loading_Screen.this.finish();
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    // Load the flashcards' information
    public void loadFlashCards(String [] chem_symbols, String [] chem_elements, boolean [] card_flipped, int elementRange) {
        // All cards are unflipped
        // Default all symbols with "test", helps with debugging
        for(int i = 0; i < card_flipped.length; i++) {
            card_flipped[i] = false;
            chem_symbols[i] = "test";
        }
        // Load elements and symbols to respective cards
        // Go by each flashcard index
        for(int j = 0; j < chem_symbols.length; j++){
            int assigner = randomizer.nextInt((elementRange + 1) - 1) + 1;
            // Assigned non-repeated information, scans
            for(int a = 0; a < chem_symbols.length; a++){
                // Compares if element has been assigned
                if(elementData.element_labels[assigner][0].equals(chem_symbols[a])){
                    // Seek new random number and reset for-loop
                    assigner = randomizer.nextInt((elementRange + 1) - 1) + 1;
                    a = 0;
                }
                // Reached end of the comparing line
                if(chem_symbols[a].equals("test")) {
                    // If no match found, assign and break
                    chem_symbols[j] = elementData.element_labels[assigner][0];
                    chem_elements[j] = elementData.element_labels[assigner][1];
                    break;
                }
            }
        }
    }
}
