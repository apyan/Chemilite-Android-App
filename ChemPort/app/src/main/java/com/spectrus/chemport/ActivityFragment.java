package com.spectrus.chemport;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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

public class ActivityFragment extends Fragment implements View.OnClickListener {

    // Variables for Activity Fragment
    AppGraphics activityFragGraphics;
    ActivityData activityData;
    PopupWindow flashPop, mcPop;
    String [] rangeNames;
    Resources res;
    Button button_00, button_01, button_02, button_03, button_04, button_05;
    TextView text_00, text_01, text_02, text_03, text_04, text_05, text_06;
    public String fontPath, fontPath_0, fontPath_1;
    public Typeface font, font_0, font_1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set fragment
        View v = inflater.inflate(R.layout.frag_activity, container, false);
        activityFragGraphics = new AppGraphics(getActivity());
        activityData = new ActivityData();

        // Set title font
        fontPath = "fonts/Titillium-Bold.ttf";
        fontPath_0 = "fonts/Titillium-Semibold.ttf";
        //fontPath = "fonts/Sansation-Bold.ttf";
        //fontPath_0 = "fonts/Sansation-Regular.ttf";
        fontPath_1 = "fonts/Rubik-Regular.ttf";
        font = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        font_0 = Typeface.createFromAsset(getActivity().getAssets(), fontPath_0);
        font_1 = Typeface.createFromAsset(getActivity().getAssets(), fontPath_1);
        TextView text_title = (TextView) v.findViewById(R.id.activity_title);
        text_title.setTypeface(font);
        text_title.setTextColor(Color.WHITE);

        // Button onClick Inputs
        Button button_00 = (Button) v.findViewById(R.id.activity_00);
        button_00.setOnClickListener(this);
        button_00.setTypeface(font_0);
        button_00.setTextColor(Color.WHITE);

        Button button_01 = (Button) v.findViewById(R.id.activity_01);
        button_01.setOnClickListener(this);
        button_01.setTypeface(font_0);
        button_01.setTextColor(Color.WHITE);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Flashcard Menu Popup
            case R.id.activity_00:
                activityData.resetData();
                LayoutInflater inflater = (LayoutInflater) getActivity().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_activity_000,
                        (ViewGroup) v.findViewById(R.id.popup_activity_000));
                flashPop = new PopupWindow(layout, activityFragGraphics.getFullWidth(),
                        activityFragGraphics.getFullHeight(), true);
                flashPop.showAtLocation(layout, Gravity.CENTER, 0, 0);
                text_00 = (TextView) flashPop.getContentView().findViewById(R.id.flashOpt_00);
                text_01 = (TextView) flashPop.getContentView().findViewById(R.id.flashOpt_01);
                text_02 = (TextView) flashPop.getContentView().findViewById(R.id.flashOpt_02);
                text_03 = (TextView) flashPop.getContentView().findViewById(R.id.flashOpt_Range);
                text_04 = (TextView) flashPop.getContentView().findViewById(R.id.screen_status);
                button_00 = (Button) flashPop.getContentView().findViewById(R.id.flashButton_00);
                button_01 = (Button) flashPop.getContentView().findViewById(R.id.flashButton_01);
                button_02 = (Button) flashPop.getContentView().findViewById(R.id.flashButton_02);
                button_03 = (Button) flashPop.getContentView().findViewById(R.id.flashButton_03);
                button_04 = (Button) flashPop.getContentView().findViewById(R.id.flashButton_04);
                text_00.setTypeface(font);
                text_01.setTypeface(font_1);
                text_02.setTypeface(font_1);
                text_03.setTypeface(font_1);
                text_04.setTypeface(font_1);
                button_00.setTypeface(font_1);
                button_01.setTypeface(font_1);
                button_02.setTypeface(font_1);
                button_03.setTypeface(font_1);
                button_04.setTypeface(font_1);
                button_00.setOnClickListener(this);
                button_01.setOnClickListener(this);
                button_02.setOnClickListener(this);
                button_03.setOnClickListener(this);
                button_04.setOnClickListener(this);
                // Set up the default texts
                res = getResources();
                activityFragGraphics.dimPopUpBackground(flashPop);
                rangeNames = res.getStringArray(R.array.flashOpt_Range);
                text_03.setText(rangeNames[activityData.default_level]);
                break;
            // Change Flashcard Mode
            case R.id.flashButton_00:
                if(activityData.default_mode){
                    // Switches button's message and changes value (Elements)
                    button_00.setText(R.string.flashOpt_001b);
                    activityData.default_mode = false;
                } else {
                    // Switches button's message and changes value (Symbols)
                    button_00.setText(R.string.flashOpt_001a);
                    activityData.default_mode = true;
                }
                break;
            // Decrease Range of Difficulty
            case R.id.flashButton_01:
                res = getResources();
                rangeNames = res.getStringArray(R.array.flashOpt_Range);
                text_03 = (TextView) flashPop.getContentView().findViewById(R.id.flashOpt_Range);
                activityData.default_level--;
                // Can't go any lower
                if(activityData.default_level < 0) {
                    activityData.default_level = 0;
                }
                text_03.setText(rangeNames[activityData.default_level]);
                break;
            // Increase Range of Difficulty
            case R.id.flashButton_02:
                res = getResources();
                rangeNames = res.getStringArray(R.array.flashOpt_Range);
                text_03 = (TextView) flashPop.getContentView().findViewById(R.id.flashOpt_Range);
                activityData.default_level++;
                // Can't go any higher
                if(activityData.default_level > 5) {
                    activityData.default_level = 5;
                }
                text_03.setText(rangeNames[activityData.default_level]);
                break;
            // Proceed to the Flashcard Screen with data
            case R.id.flashButton_03:
                // New activity
                activityData.activityValue = "FLASHCARD_0";
                Intent dataFlash = new Intent(getActivity(), Loading_Screen.class);
                // Activity Data Block
                dataFlash.putExtra("gameActivity", activityData.activityValue);
                dataFlash.putExtra("gameMode", activityData.default_mode);
                dataFlash.putExtra("gameLevel", activityData.default_level);
                dataFlash.putExtra("gamePhase", activityData.gameMode);
                startActivity(dataFlash);
                flashPop.dismiss();
                break;
            // Back and Exit the Flashcard Popup
            case R.id.flashButton_04:
                flashPop.dismiss();
                break;

            // To Multiple Choice Popup
            case R.id.activity_01:
                activityData.resetData();
                LayoutInflater inflater_0 = (LayoutInflater) getActivity().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout_0 = inflater_0.inflate(R.layout.popup_activity_001,
                        (ViewGroup) v.findViewById(R.id.popup_activity_001));
                mcPop = new PopupWindow(layout_0, activityFragGraphics.getFullWidth(),
                        activityFragGraphics.getFullHeight(), true);
                mcPop.showAtLocation(layout_0, Gravity.CENTER, 0, 0);
                text_00 = (TextView) mcPop.getContentView().findViewById(R.id.popup_title);
                text_01 = (TextView) mcPop.getContentView().findViewById(R.id.mcOpt_000);
                text_02 = (TextView) mcPop.getContentView().findViewById(R.id.mcOpt_001);
                text_03 = (TextView) mcPop.getContentView().findViewById(R.id.mcOpt_002);
                text_04 = (TextView) mcPop.getContentView().findViewById(R.id.mcOpt_003);
                text_05 = (TextView) mcPop.getContentView().findViewById(R.id.screen_status);
                text_06 = (TextView) mcPop.getContentView().findViewById(R.id.screen_status_0);
                button_00 = (Button) mcPop.getContentView().findViewById(R.id.mcButton_000);
                button_01 = (Button) mcPop.getContentView().findViewById(R.id.mcButton_001);
                button_02 = (Button) mcPop.getContentView().findViewById(R.id.mcButton_002);
                button_03 = (Button) mcPop.getContentView().findViewById(R.id.mcButton_003);
                button_04 = (Button) mcPop.getContentView().findViewById(R.id.mcButton_004);
                button_05 = (Button) mcPop.getContentView().findViewById(R.id.mcButton_005);
                text_00.setTypeface(font);
                text_01.setTypeface(font_1);
                text_02.setTypeface(font_1);
                text_03.setTypeface(font_1);
                text_04.setTypeface(font_1);
                text_05.setTypeface(font_1);
                text_06.setTypeface(font_1);
                button_00.setTypeface(font_1);
                button_01.setTypeface(font_1);
                button_02.setTypeface(font_1);
                button_03.setTypeface(font_1);
                button_04.setTypeface(font_1);
                button_05.setTypeface(font_1);
                button_00.setOnClickListener(this);
                button_01.setOnClickListener(this);
                button_02.setOnClickListener(this);
                button_03.setOnClickListener(this);
                button_04.setOnClickListener(this);
                button_05.setOnClickListener(this);
                // Set up the default texts
                res = getResources();
                activityFragGraphics.dimPopUpBackground(mcPop);
                rangeNames = res.getStringArray(R.array.mcOpt_Range);
                text_03.setText(rangeNames[activityData.default_level]);
                break;
            // Change Multiple Choice Mode
            case R.id.mcButton_000:
                if(activityData.default_mode){
                    // Switches button's message and changes value (Elements)
                    button_00.setText(R.string.mcOpt_001b);
                    activityData.default_mode = false;
                } else {
                    // Switches button's message and changes value (Symbols)
                    button_00.setText(R.string.mcOpt_001a);
                    activityData.default_mode = true;
                }
                break;
            // Decrease Range of Difficulty
            case R.id.mcButton_001:
                res = getResources();
                rangeNames = res.getStringArray(R.array.mcOpt_Range);
                text_03 = (TextView) mcPop.getContentView().findViewById(R.id.mcOpt_002);
                activityData.default_level--;
                // Can't go any lower
                if(activityData.default_level < 0) {
                    activityData.default_level = 0;
                }
                text_03.setText(rangeNames[activityData.default_level]);
                break;
            // Increase Range of Difficulty
            case R.id.mcButton_002:
                res = getResources();
                rangeNames = res.getStringArray(R.array.mcOpt_Range);
                text_03 = (TextView) mcPop.getContentView().findViewById(R.id.mcOpt_002);
                activityData.default_level++;
                // Can't go any higher
                if(activityData.default_level > 4) {
                    activityData.default_level = 4;
                }
                text_03.setText(rangeNames[activityData.default_level]);
                break;
            // Change Multiple Choice Game Mode
            case R.id.mcButton_003:
                if(activityData.gameMode[1] == 0){
                    // Switches button's message and changes value (Timer)
                    button_03.setText(R.string.mcOpt_003b);
                    text_06.setText(R.string.mc_note_001);
                    activityData.gameMode[1] = 1;
                } else {
                    // Switches button's message and changes value (Leisure)
                    button_03.setText(R.string.mcOpt_003a);
                    text_06.setText(R.string.mc_note_000);
                    activityData.gameMode[1] = 0;
                }
                break;
            // Proceed to the Multiple Choice Screen with data
            case R.id.mcButton_004:
                // New activity
                activityData.activityValue = "MULTICHOICE_0";
                Intent dataMC = new Intent(getActivity(), Loading_Screen.class);
                // Activity Data Block
                dataMC.putExtra("gameActivity", activityData.activityValue);
                dataMC.putExtra("gameMode", activityData.default_mode);
                dataMC.putExtra("gameLevel", activityData.default_level);
                dataMC.putExtra("gamePhase", activityData.gameMode);
                startActivity(dataMC);
                mcPop.dismiss();
                break;
            // Back and Exit the Multiple Choice Popup
            case R.id.mcButton_005:
                mcPop.dismiss();
                break;
            default:
                break;
        }
    }
}
