package com.spectrus.chemport;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.spectrus.chemport.AppData.ActivityData;
import com.spectrus.chemport.AppFunction.AppGraphics;

public class IndexFragment extends Fragment implements View.OnClickListener {

    // Variables for the fragment
    PopupWindow infoPop;
    AppGraphics fragIndexGraphics;
    ActivityData activityData;

    public String fontPath, fontPath_0, fontPath_1;
    public Typeface font, font_0, font_1;
    Button button_000;
    TextView text_000, text_001, text_002, text_003, text_004, text_005;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set fragment
        View v = inflater.inflate(R.layout.frag_index, container, false);
        fragIndexGraphics = new AppGraphics(getActivity());
        activityData = new ActivityData();

        // Set font resources
        fontPath = "fonts/Titillium-Bold.ttf";
        fontPath_0 = "fonts/Titillium-Semibold.ttf";
        fontPath_1 = "fonts/Rubik-Regular.ttf";
        //String fontPath = "fonts/Sansation-Bold.ttf";
        //String fontPath_0 = "fonts/Sansation-Regular.ttf";
        font = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        font_0 = Typeface.createFromAsset(getActivity().getAssets(), fontPath_0);
        font_1 = Typeface.createFromAsset(getActivity().getAssets(), fontPath_1);
        TextView text_title = (TextView) v.findViewById(R.id.index_title);
        text_title.setTypeface(font);
        text_title.setTextColor(Color.WHITE);

        // Button onClick Inputs
        Button button_00 = (Button) v.findViewById(R.id.index_00);
        button_00.setOnClickListener(this);
        button_00.setTypeface(font_0);
        button_00.setTextColor(Color.WHITE);
        Button button_01 = (Button) v.findViewById(R.id.index_01);
        button_01.setOnClickListener(this);
        button_01.setTypeface(font_0);
        button_01.setTextColor(Color.WHITE);
        Button button_02 = (Button) v.findViewById(R.id.index_02);
        button_02.setOnClickListener(this);
        button_02.setTypeface(font_0);
        button_02.setTextColor(Color.WHITE);
        Button button_03 = (Button) v.findViewById(R.id.index_03);
        button_03.setOnClickListener(this);
        button_03.setTypeface(font_0);
        button_03.setTextColor(Color.WHITE);
        Button button_04 = (Button) v.findViewById(R.id.index_04);
        button_04.setOnClickListener(this);
        button_04.setTypeface(font_0);
        button_04.setTextColor(Color.WHITE);
        Button button_05 = (Button) v.findViewById(R.id.index_05);
        button_05.setOnClickListener(this);
        button_05.setTypeface(font_0);
        button_05.setTextColor(Color.WHITE);
        Button button_06 = (Button) v.findViewById(R.id.index_06);
        button_06.setOnClickListener(this);
        button_06.setTypeface(font_0);
        button_06.setTextColor(Color.WHITE);
        Button button_07 = (Button) v.findViewById(R.id.index_07);
        button_07.setOnClickListener(this);
        button_07.setTypeface(font_0);
        button_07.setTextColor(Color.WHITE);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Periodic Table
            case R.id.index_00:
                Intent i_00 = new Intent(getActivity(), PeriodicTableScreen.class);
                startActivity(i_00);
                break;
            // Glossary
            case R.id.index_01:
                activityData.resetData();
                // New activity
                activityData.activityValue = "GLOSSARY";
                Intent i_01 = new Intent(getActivity(), Loading_Screen.class);
                i_01.putExtra("gameActivity", activityData.activityValue);
                startActivity(i_01);
                break;
            // About ChemPort
            case R.id.index_02:
                Intent i_02 = new Intent(getActivity(), ReaderScreen.class);
                i_02.putExtra("readerCode", "ABOUT_CP");
                startActivity(i_02);
                break;
            // App Guide
            case R.id.index_03:

                break;
            // Lesson Help
            case R.id.index_04:

                break;
            // Activity Help
            case R.id.index_05:

                break;
            // Bulletin
            case R.id.index_06:

                break;
            // Credits
            case R.id.index_07:
                // Credits Popup
                LayoutInflater inflater_2 = (LayoutInflater) getActivity().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater_2.inflate(R.layout.popup_index_000,
                        (ViewGroup) v.findViewById(R.id.popup_index_000));
                infoPop = new PopupWindow(layout, fragIndexGraphics.getFullWidth(),
                        fragIndexGraphics.getFullHeight(), true);
                infoPop.showAtLocation(layout, Gravity.CENTER, 0, 0);
                text_000 = (TextView) infoPop.getContentView().findViewById(R.id.credits_title);
                text_001 = (TextView) infoPop.getContentView().findViewById(R.id.text_00);
                text_002 = (TextView) infoPop.getContentView().findViewById(R.id.text_01);
                text_003 = (TextView) infoPop.getContentView().findViewById(R.id.text_02);
                text_004 = (TextView) infoPop.getContentView().findViewById(R.id.text_03);
                text_005 = (TextView) infoPop.getContentView().findViewById(R.id.text_04);
                text_000.setTypeface(font);
                text_001.setTypeface(font_1);
                text_002.setTypeface(font_1);
                text_003.setTypeface(font_1);
                text_004.setTypeface(font_1);
                text_005.setTypeface(font_1);
                button_000 = (Button) infoPop.getContentView().findViewById(R.id.credits_close);
                button_000.setTypeface(font_1);
                button_000.setOnClickListener(this);
                fragIndexGraphics.dimPopUpBackground(infoPop);
                break;
            // Close Credits Popup
            case R.id.credits_close:
                infoPop.dismiss();
                break;
            default:
                break;
        }
    }

}
