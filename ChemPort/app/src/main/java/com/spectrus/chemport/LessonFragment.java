package com.spectrus.chemport;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.spectrus.chemport.AppData.ActivityData;
import com.spectrus.chemport.AppFunction.AppGraphics;

public class LessonFragment extends Fragment implements View.OnClickListener {

    // Variables for Activity Fragment
    AppGraphics activityFragGraphics;
    ActivityData activityData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set fragment
        View v = inflater.inflate(R.layout.frag_lesson, container, false);
        activityFragGraphics = new AppGraphics(getActivity());
        activityData = new ActivityData();

        // Set title font
        String fontPath = "fonts/Titillium-Bold.ttf";
        String fontPath_0 = "fonts/Titillium-Semibold.ttf";
        //String fontPath = "fonts/Sansation-Bold.ttf";
        //String fontPath_0 = "fonts/Sansation-Regular.ttf";
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        Typeface font_0 = Typeface.createFromAsset(getActivity().getAssets(), fontPath_0);
        TextView text_title = (TextView) v.findViewById(R.id.lessons_title);
        text_title.setTypeface(font);
        text_title.setTextColor(Color.WHITE);

        // Button onClick Inputs
        Button button_00 = (Button) v.findViewById(R.id.lessons_00);
        button_00.setOnClickListener(this);
        button_00.setTypeface(font_0);
        button_00.setTextColor(Color.WHITE);
        Button button_01 = (Button) v.findViewById(R.id.lessons_01);
        button_01.setOnClickListener(this);
        button_01.setTypeface(font_0);
        button_01.setTextColor(Color.WHITE);
        Button button_02 = (Button) v.findViewById(R.id.lessons_02);
        button_02.setOnClickListener(this);
        button_02.setTypeface(font_0);
        button_02.setTextColor(Color.WHITE);
        Button button_03 = (Button) v.findViewById(R.id.lessons_03);
        button_03.setOnClickListener(this);
        button_03.setTypeface(font_0);
        button_03.setTextColor(Color.WHITE);
        Button button_04 = (Button) v.findViewById(R.id.lessons_04);
        button_04.setOnClickListener(this);
        button_04.setTypeface(font_0);
        button_04.setTextColor(Color.WHITE);
        Button button_05 = (Button) v.findViewById(R.id.lessons_05);
        button_05.setOnClickListener(this);
        button_05.setTypeface(font_0);
        button_05.setTextColor(Color.WHITE);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Introductory
            case R.id.lessons_00:
                Intent i = new Intent(getActivity(), ReaderScreen.class);
                i.putExtra("readerCode", "LESSON_00");
                startActivity(i);
                break;
            // Scientific Method
            case R.id.lessons_01:
                Intent i0 = new Intent(getActivity(), ReaderScreen.class);
                i0.putExtra("readerCode", "LESSON_01");
                startActivity(i0);
                break;
            // Mole and Molar Mass
            case R.id.lessons_02:

                break;
            // Electron Configuration
            case R.id.lessons_03:

                break;
            // Electron Configuration
            case R.id.lessons_04:

                break;
            // Electron Configuration
            case R.id.lessons_05:

                break;
            default:
                break;
        }
    }

}
