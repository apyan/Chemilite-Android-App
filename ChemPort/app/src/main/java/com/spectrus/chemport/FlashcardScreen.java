package com.spectrus.chemport;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spectrus.chemport.AppData.ActivityData;
import com.spectrus.chemport.AppData.ElementData;
import com.spectrus.chemport.AppFunction.AppGraphics;

import java.util.Random;

public class FlashcardScreen extends Activity {

    // Variables of Flashcard activity
    public long buttonClickTime = 0;
    public long TIME_THRESHOLD = 500;
    public Typeface textFont;
    AppGraphics flashcardGraphics;

    // Flashcard Variables
    public boolean gameMode;
    public String [] chem_symbols;
    public String [] chem_elements;
    public boolean [] card_flipped;
    public int cardMaximum;

    public int cardCurrent;
    public AnimatorSet leftIn;
    public AnimatorSet rightOut;
    TextView text_00, front_00, back_00;
    Button button_00, button_01, button_02, button_03;
    FrameLayout flashcardFront, flashcardBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_screen);

        flashcardGraphics = new AppGraphics(FlashcardScreen.this);

        String fontPath = "fonts/Rubik-Regular.ttf";
        textFont = Typeface.createFromAsset(getAssets(), fontPath);

        leftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flip_left_in);
        rightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flip_right_out);

        // Assign the layout IDs to proper view
        flashcardFront = (FrameLayout)findViewById(R.id.flashcard_front);
        flashcardBack = (FrameLayout)findViewById(R.id.flashcard_back);
        front_00 = (TextView)findViewById(R.id.card_front);
        front_00.setTypeface(textFont);
        back_00 = (TextView)findViewById(R.id.card_back);
        back_00.setTypeface(textFont);
        button_00 = (Button)findViewById(R.id.button_00);
        button_01 = (Button)findViewById(R.id.button_01);
        button_02 = (Button)findViewById(R.id.button_02);
        button_03 = (Button)findViewById(R.id.button_03);
        button_00.setTypeface(textFont);
        button_01.setTypeface(textFont);
        button_02.setTypeface(textFont);
        button_03.setTypeface(textFont);

        // Obtain Flashcard Setup Information
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameMode = extras.getBoolean("gameMode");
            chem_symbols = extras.getStringArray("chemSymbols");
            chem_elements = extras.getStringArray("chemElements");
            card_flipped = extras.getBooleanArray("cardFlipped");;
            cardMaximum = extras.getInt("cardMax");
        }

        cardCurrent = 1;
        flashcardPreparation();
    }

    // Flashcard Screen Preparation
    public void flashcardPreparation(){
        counterRefresh(cardCurrent, cardMaximum);
        // First Card Preparation
        updateCardText();
    }

    // To Previous Card
    public void onPrevCard(View v){
        // Prevent multi-clicking, threshold of 500 ms
        if (SystemClock.elapsedRealtime() - buttonClickTime < TIME_THRESHOLD){
            return;
        }
        buttonClickTime = SystemClock.elapsedRealtime();

        card_flipped[cardCurrent - 1] = false;
        cardCurrent--;
        if(cardCurrent < 1) {
            cardCurrent = 1;
            return;
        }
        counterRefresh(cardCurrent, cardMaximum);
        shiftFlashcard(0);
        updateCardText();
    }

    // To Flip Card
    public void onFlipCard(View v){
        // Prevent multi-clicking, threshold of 500 ms
        if (SystemClock.elapsedRealtime() - buttonClickTime < TIME_THRESHOLD){
            return;
        }
        buttonClickTime = SystemClock.elapsedRealtime();

        // If the card isn't flipped
        if(card_flipped[cardCurrent - 1] == false) {
            rightOut.setTarget(flashcardFront);
            leftIn.setTarget(flashcardBack);
            rightOut.start();
            leftIn.start();
            card_flipped[cardCurrent - 1] = true;
        } else {
            rightOut.setTarget(flashcardBack);
            leftIn.setTarget(flashcardFront);
            rightOut.start();
            leftIn.start();
            card_flipped[cardCurrent - 1] = false;
        }
    }

    // To Next Card
    public void onNextCard(View v){
        // Prevent multi-clicking, threshold of 500 ms
        if (SystemClock.elapsedRealtime() - buttonClickTime < TIME_THRESHOLD){
            return;
        }
        buttonClickTime = SystemClock.elapsedRealtime();

        card_flipped[cardCurrent - 1] = false;
        cardCurrent++;
        if(cardCurrent > cardMaximum) {
            cardCurrent = cardMaximum;
            return;
        }
        counterRefresh(cardCurrent, cardMaximum);
        shiftFlashcard(1);
        updateCardText();
    }

    // Update Flashcard Content, symbols and elements
    public void updateCardText(){
        // Test by Symbols
        if(gameMode) {
            front_00.setText(chem_symbols[cardCurrent - 1]);
            back_00.setText(chem_elements[cardCurrent - 1]);
        } else {
            // Test by Elements
            front_00.setText(chem_elements[cardCurrent - 1]);
            back_00.setText(chem_symbols[cardCurrent - 1]);
        }
    }

    // To Exit the screen
    public void onFlashExit(View v){
        finish();
    }

    // Refresh Card Counter
    public void counterRefresh(int cardCurrent, int cardMaximum){
        // If current card is lower than 1
        if(cardCurrent < 1) cardCurrent = 1;
        // If current card is greater than maximum
        if(cardCurrent > cardMaximum) cardCurrent = cardMaximum;
        String refresher = cardCurrent + " / " + cardMaximum;
        text_00 = (TextView)findViewById(R.id.text_00);
        text_00.setText(refresher);
        text_00.setTypeface(textFont);
    }

    // Shifts left or right (animation) of the next/previous flashcard
    public void shiftFlashcard(int direction){
        // Reset RelativeLayout
        flashcardBack.setAlpha(0);
        flashcardBack.setRotationY(0);
        flashcardFront.setAlpha(1);
        flashcardFront.setRotationY(0);

    }
}
