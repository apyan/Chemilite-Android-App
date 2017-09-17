package com.spectrus.chemport;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.spectrus.chemport.AppFunction.AppGraphics;

public class ReaderScreen extends Activity {

    // Variables needed for the Reader
    Context context;
    public int pageNumberCurrent = 0;
    public int pageNumberMax = 0;
    public String lessonSect;

    // Reader info display
    public String [] text_hold;
    public String [] img_hold;
    public String chapter_title;

    // Display data
    public TextView reader_title, page_current, text_title, text_0, text_1;
    public Button prev_page, next_page, exit_reader;
    public ImageView image_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_screen);
        context = ReaderScreen.this;

        String fontPath = "fonts/Rubik-Regular.ttf";
        Typeface font = Typeface.createFromAsset(getAssets(), fontPath);

        reader_title = (TextView) findViewById(R.id.current_title);
        page_current = (TextView) findViewById(R.id.card_back);
        text_title = (TextView) findViewById(R.id.text_title);
        text_0 = (TextView) findViewById(R.id.text_0);
        text_1 = (TextView) findViewById(R.id.text_1);
        prev_page = (Button) findViewById(R.id.button_prev);
        next_page = (Button) findViewById(R.id.button_next);
        exit_reader = (Button) findViewById(R.id.button_back);
        image_0 = (ImageView) findViewById(R.id.image_0);
        reader_title.setTypeface(font);
        page_current.setTypeface(font);
        text_title.setTypeface(font);
        text_0.setTypeface(font);
        text_1.setTypeface(font);
        prev_page.setTypeface(font);
        next_page.setTypeface(font);
        exit_reader.setTypeface(font);

        // Obtain Reader Setup Information
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lessonSect = extras.getString("readerCode");
        }

        loadReaderArray();
    }

    // Turns to Previous Page
    public void onPreviousPage(View v){
        if(pageNumberCurrent == 0) {
            pageNumberCurrent = 0;
        } else {
            pageNumberCurrent--;
        }
        // Set Page
        text_0.setText(text_hold[pageNumberCurrent * 2]);
        text_1.setText(text_hold[(pageNumberCurrent * 2) + 1]);
        //image_0.setImageResource(img_hold[pageNumberCurrent]);
        imageSelectFix(image_0, img_hold[pageNumberCurrent]);

        // Create Title Label if on first page
        if(pageNumberCurrent == 0) {
            text_title.setText(chapter_title);
        } else {
            text_title.setText("");
        }
        updatePageCounter();
    }

    // Turns to Next Page
    public void onNextPage(View v){
        if(pageNumberCurrent == (pageNumberMax - 1)) {
            pageNumberCurrent = (pageNumberMax - 1);
        } else {
            pageNumberCurrent++;
        }
        // Set Page
        text_0.setText(text_hold[pageNumberCurrent * 2]);
        text_1.setText(text_hold[(pageNumberCurrent * 2) + 1]);
        //image_0.setImageResource(img_hold[pageNumberCurrent]);
        imageSelectFix(image_0, img_hold[pageNumberCurrent]);

        // Create Title Label if on first page
        if(pageNumberCurrent == 0) {
            text_title.setText(chapter_title);
        } else {
            text_title.setText("");
        }
        updatePageCounter();
    }

    // Update the Page Counter
    public void updatePageCounter() {
        String updater = " / ";
        page_current.setText((pageNumberCurrent + 1) + updater + pageNumberMax);
    }

    // Exits out of the Reader
    public void onBackButton(View v){
        finish();
    }

    // Trim to specific dimension for certain images
    public void imageSelectFix(ImageView image, String imageID){
        switch (imageID) {
            case "buffer_square":
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buffer_square));
                image.getLayoutParams().height = (int) getResources().getDimension(R.dimen.buffer_h);
                image.getLayoutParams().width = (int) getResources().getDimension(R.dimen.buffer_w);
                break;
            case "reader_logo":
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.reader_logo));
                image.getLayoutParams().height = (int) getResources().getDimension(R.dimen.reader_h);
                image.getLayoutParams().width = (int) getResources().getDimension(R.dimen.reader_w);
                break;
            case "lesson_00_0":
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.lesson_00_0));
                image.getLayoutParams().height = (int) getResources().getDimension(R.dimen.l_00_0_h);
                image.getLayoutParams().width = (int) getResources().getDimension(R.dimen.l_00_0_w);
                break;
            case "lesson_00_1":
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.lesson_00_1));
                image.getLayoutParams().height = (int) getResources().getDimension(R.dimen.l_00_1_h);
                image.getLayoutParams().width = (int) getResources().getDimension(R.dimen.l_00_1_w);
                break;
            case "lesson_00_2":
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.lesson_00_2));
                image.getLayoutParams().height = (int) getResources().getDimension(R.dimen.l_00_2_h);
                image.getLayoutParams().width = (int) getResources().getDimension(R.dimen.l_00_2_w);
                break;
            default:
                break;
        }
    }

    // Load Array Data depending on readerCode
    public void loadReaderArray() {
        Resources res = getResources();
        switch (lessonSect){
            // About ChemPort
            case "ABOUT_CP":
                chapter_title = getString(R.string.about_cp);
                text_hold = res.getStringArray(R.array.about_cp_text);
                img_hold = res.getStringArray(R.array.about_cp_img);
                break;
            // Introduction
            case "LESSON_00":
                chapter_title = getString(R.string.lesson_head_000);
                text_hold = res.getStringArray(R.array.lesson_000_text);
                img_hold = res.getStringArray(R.array.lesson_000_img);
                break;
            // Scientific Method
            case "LESSON_01":
                chapter_title = getString(R.string.lesson_head_001);
                text_hold = res.getStringArray(R.array.lesson_001_text);
                img_hold = res.getStringArray(R.array.lesson_001_img);
                break;
            default:
                finish();
                break;
        }
        reader_title.setText(chapter_title);
        text_0.setText(text_hold[0]);
        //image_0.setImageResource(img_hold[0]);
        imageSelectFix(image_0, img_hold[0]);
        text_1.setText(text_hold[1]);
        text_title.setText(chapter_title);
        pageNumberMax = img_hold.length;
        updatePageCounter();
    }
}
