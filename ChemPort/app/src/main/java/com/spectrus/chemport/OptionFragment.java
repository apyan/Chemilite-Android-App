package com.spectrus.chemport;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.spectrus.chemport.AppFunction.AppGraphics;
import com.spectrus.chemport.AppFunction.AppStorage;

public class OptionFragment extends Fragment implements View.OnClickListener {

    // Variables for the fragment
    AppGraphics fragOptionGraphics;
    AppStorage fileReader;

    PopupWindow titleScreenPop, profileScreenPop, nameChangePop, clearDataPop;
    Resources res;
    Context context;
    TextView text_00, text_01, text_02, text_03, text_04, text_05, text_06, text_07, text_08;
    Button button_00, button_01, button_02;
    EditText editText_00;

    public String fontPath, fontPath_0, fontPath_1;
    public Typeface font, font_0, font_1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set fragment
        View v = inflater.inflate(R.layout.frag_option, container, false);
        fragOptionGraphics = new AppGraphics(getActivity());
        context = getActivity();

        // Set title font
        fontPath = "fonts/Titillium-Bold.ttf";
        fontPath_0 = "fonts/Titillium-Semibold.ttf";
        //fontPath = "fonts/Sansation-Bold.ttf";
        //fontPath_0 = "fonts/Sansation-Regular.ttf";
        fontPath_1 = "fonts/Rubik-Regular.ttf";
        font = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        font_0 = Typeface.createFromAsset(getActivity().getAssets(), fontPath_0);
        font_1 = Typeface.createFromAsset(getActivity().getAssets(), fontPath_1);
        TextView text_title = (TextView) v.findViewById(R.id.options_title);
        text_title.setTypeface(font);
        text_title.setTextColor(Color.WHITE);

        // Button onClick Inputs
        Button button_000 = (Button) v.findViewById(R.id.options_00);
        button_000.setOnClickListener(this);
        button_000.setTypeface(font_0);
        button_000.setTextColor(Color.WHITE);
        Button button_001 = (Button) v.findViewById(R.id.options_01);
        button_001.setOnClickListener(this);
        button_001.setTypeface(font_0);
        button_001.setTextColor(Color.WHITE);
        Button button_002 = (Button) v.findViewById(R.id.options_02);
        button_002.setOnClickListener(this);
        button_002.setTypeface(font_0);
        button_002.setTextColor(Color.WHITE);
        Button button_003 = (Button) v.findViewById(R.id.options_03);
        button_003.setOnClickListener(this);
        button_003.setTypeface(font_0);
        button_003.setTextColor(Color.WHITE);
        Button button_004 = (Button) v.findViewById(R.id.options_04);
        button_004.setOnClickListener(this);
        button_004.setTypeface(font_0);
        button_004.setTextColor(Color.WHITE);
        Button button_005 = (Button) v.findViewById(R.id.options_05);
        button_005.setOnClickListener(this);
        button_005.setTypeface(font_0);
        button_005.setTextColor(Color.WHITE);
        Button button_006 = (Button) v.findViewById(R.id.options_06);
        button_006.setOnClickListener(this);
        button_006.setTypeface(font_0);
        button_006.setTextColor(Color.WHITE);
        Button button_007 = (Button) v.findViewById(R.id.options_07);
        button_007.setOnClickListener(this);
        button_007.setTypeface(font_0);
        button_007.setTextColor(Color.WHITE);
        Button button_008 = (Button) v.findViewById(R.id.options_08);
        button_008.setOnClickListener(this);
        button_008.setTypeface(font_0);
        button_008.setTextColor(Color.WHITE);
        Button button_009 = (Button) v.findViewById(R.id.options_09);
        button_009.setOnClickListener(this);
        button_009.setTypeface(font_0);
        button_009.setTextColor(Color.WHITE);
        Button button_010 = (Button) v.findViewById(R.id.options_10);
        button_010.setOnClickListener(this);
        button_010.setTypeface(font_0);
        button_010.setTextColor(Color.WHITE);

        Button button_011 = (Button) v.findViewById(R.id.options_11);
        button_011.setOnClickListener(this);
        button_011.setTypeface(font_0);
        button_011.setTextColor(Color.WHITE);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Profile Screen
            case R.id.options_00:
                // File Reading Set-Up
                fileReader = new AppStorage(context, "user-cp-s.cp");
                fileReader.dataRead();

                LayoutInflater inflater_0 = (LayoutInflater) getActivity().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout_0 = inflater_0.inflate(R.layout.popup_options_001,
                        (ViewGroup) v.findViewById(R.id.popup_options_001));
                profileScreenPop = new PopupWindow(layout_0, fragOptionGraphics.getFullWidth(),
                        fragOptionGraphics.getFullHeight(), true);
                profileScreenPop.showAtLocation(layout_0, Gravity.CENTER, 0, 0);
                text_00 = (TextView) profileScreenPop.getContentView().findViewById(R.id.screen_title);
                text_01 = (TextView) profileScreenPop.getContentView().findViewById(R.id.text_00);
                text_02 = (TextView) profileScreenPop.getContentView().findViewById(R.id.text_01);
                text_03 = (TextView) profileScreenPop.getContentView().findViewById(R.id.text_02);
                text_04 = (TextView) profileScreenPop.getContentView().findViewById(R.id.text_03);
                text_05 = (TextView) profileScreenPop.getContentView().findViewById(R.id.text_04);
                text_06 = (TextView) profileScreenPop.getContentView().findViewById(R.id.text_05);
                text_07 = (TextView) profileScreenPop.getContentView().findViewById(R.id.screen_medals);
                text_08 = (TextView) profileScreenPop.getContentView().findViewById(R.id.no_medals);
                text_00.setTypeface(font);
                text_01.setTypeface(font_1);
                text_02.setTypeface(font_1);
                text_03.setTypeface(font_1);
                text_04.setTypeface(font_1);
                text_05.setTypeface(font_1);
                text_06.setTypeface(font_1);
                text_07.setTypeface(font_1);
                text_08.setTypeface(font_1);
                button_00 = (Button) profileScreenPop.getContentView().findViewById(R.id.change_name);
                button_01 = (Button) profileScreenPop.getContentView().findViewById(R.id.profile_back);
                button_00.setTypeface(font_1);
                button_01.setTypeface(font_1);
                button_00.setOnClickListener(this);
                button_01.setOnClickListener(this);
                fragOptionGraphics.dimPopUpBackground(profileScreenPop);
                // Set Up Name and Dates
                text_02.setText(fileReader.dataRead[0]);
                text_04.setText(fileReader.extractUSDate(1));
                text_06.setText(fileReader.extractUSDate(2));
                // If no medals are earned
                if(fileReader.dataRead[12].equals("0000000000")) {
                    text_08.setVisibility(View.VISIBLE);
                } else {
                    text_08.setVisibility(View.INVISIBLE);
                }
                break;
            // Change Name Button
            case R.id.change_name:
                // Heads to Name Change Popup
                profileScreenPop.dismiss();

                // File Reading Set-Up
                fileReader = new AppStorage(context, "user-cp-s.cp");
                fileReader.dataRead();

                LayoutInflater inflater_1 = (LayoutInflater) getActivity().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout_1 = inflater_1.inflate(R.layout.popup_options_002,
                        (ViewGroup) v.findViewById(R.id.popup_options_002));
                nameChangePop = new PopupWindow(layout_1, fragOptionGraphics.getFullWidth(),
                        fragOptionGraphics.getFullHeight(), true);
                nameChangePop.showAtLocation(layout_1, Gravity.CENTER, 0, 0);
                editText_00 = (EditText) nameChangePop.getContentView().findViewById(R.id.editText_00);
                text_00 = (TextView) nameChangePop.getContentView().findViewById(R.id.screen_title);
                text_01 = (TextView) nameChangePop.getContentView().findViewById(R.id.text_00);
                text_02 = (TextView) nameChangePop.getContentView().findViewById(R.id.text_01);
                text_00.setTypeface(font);
                text_01.setTypeface(font_1);
                text_02.setTypeface(font_1);
                button_00 = (Button) nameChangePop.getContentView().findViewById(R.id.button_cancel);
                button_01 = (Button) nameChangePop.getContentView().findViewById(R.id.button_submit);
                button_00.setTypeface(font_1);
                button_01.setTypeface(font_1);
                button_00.setOnClickListener(this);
                button_01.setOnClickListener(this);
                editText_00.setText(fileReader.dataRead[0]);
                fragOptionGraphics.dimPopUpBackground(nameChangePop);
                break;

            // Close Name Change Screen Popup
            case R.id.button_cancel:
                nameChangePop.dismiss();
                break;

            // Changes Name in Name Change Screen Popup
            case R.id.button_submit:
                String newName = editText_00.getText().toString();
                if(newName.trim().length() < 5){
                    text_02.setText(R.string.name_change_003b);
                } else {
                    fileReader.dataRead[0] = newName;
                    fileReader.dataWrite();
                    nameChangePop.dismiss();
                }
                break;

            // Close Profile Screen Popup
            case R.id.profile_back:
                profileScreenPop.dismiss();
                break;

            // Title Screen
            case R.id.options_01:
                // File Reading Set-Up
                fileReader = new AppStorage(context, "user-cp-s.cp");
                fileReader.dataRead();

                // ON = 0 = allow screen, OFF = 1 = skip screen
                LayoutInflater inflater = (LayoutInflater) getActivity().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_options_000,
                        (ViewGroup) v.findViewById(R.id.popup_options_000));
                titleScreenPop = new PopupWindow(layout, fragOptionGraphics.getFullWidth(),
                        fragOptionGraphics.getFullHeight(), true);
                titleScreenPop.showAtLocation(layout, Gravity.CENTER, 0, 0);
                text_00 = (TextView) titleScreenPop.getContentView().findViewById(R.id.screen_title);
                text_01 = (TextView) titleScreenPop.getContentView().findViewById(R.id.openApp);
                text_02 = (TextView) titleScreenPop.getContentView().findViewById(R.id.leaveApp);
                text_03 = (TextView) titleScreenPop.getContentView().findViewById(R.id.screen_status);
                text_00.setTypeface(font);
                text_01.setTypeface(font_1);
                text_02.setTypeface(font_1);
                text_03.setTypeface(font_1);
                button_00 = (Button) titleScreenPop.getContentView().findViewById(R.id.opening_button);
                button_01 = (Button) titleScreenPop.getContentView().findViewById(R.id.leave_button);
                button_02 = (Button) titleScreenPop.getContentView().findViewById(R.id.button_back);
                button_00.setTypeface(font_1);
                button_01.setTypeface(font_1);
                button_02.setTypeface(font_1);
                button_00.setOnClickListener(this);
                button_01.setOnClickListener(this);
                button_02.setOnClickListener(this);
                text_03.setText(R.string.title_screen_003);
                res = getResources();
                fragOptionGraphics.dimPopUpBackground(titleScreenPop);
                // Set Up Button Text
                if(fileReader.dataRead[6].charAt(0) == '0') {
                    button_00.setText(R.string.title_screen_on);
                } else {
                    button_00.setText(R.string.title_screen_off);
                }
                if(fileReader.dataRead[6].charAt(1) == '0') {
                    button_01.setText(R.string.title_screen_on);
                } else {
                    button_01.setText(R.string.title_screen_off);
                }
                //Toast.makeText(getActivity(), fileReader.dataRead[6], Toast.LENGTH_SHORT).show();
                break;
            // If the Opening Title were to be determined
            case R.id.opening_button:
                if(fileReader.dataRead[6].charAt(0) == '0') {
                    StringBuilder update = new StringBuilder(fileReader.dataRead[6]);
                    update.setCharAt(0, '1');
                    fileReader.dataRead[6] = update.toString();
                    button_00.setText(R.string.title_screen_off);
                } else {
                    StringBuilder update = new StringBuilder(fileReader.dataRead[6]);
                    update.setCharAt(0, '0');
                    fileReader.dataRead[6] = update.toString();
                    button_00.setText(R.string.title_screen_on);
                }
                fileReader.dataWrite();
                //Toast.makeText(getActivity(), fileReader.dataRead[6], Toast.LENGTH_SHORT).show();
                break;
            // If the Leaving Title were to be determined
            case R.id.leave_button:
                if(fileReader.dataRead[6].charAt(1) == '0') {
                    StringBuilder update = new StringBuilder(fileReader.dataRead[6]);
                    update.setCharAt(1, '1');
                    fileReader.dataRead[6] = update.toString();
                    button_01.setText(R.string.title_screen_off);
                } else {
                    StringBuilder update = new StringBuilder(fileReader.dataRead[6]);
                    update.setCharAt(1, '0');
                    fileReader.dataRead[6] = update.toString();
                    button_01.setText(R.string.title_screen_on);
                }
                fileReader.dataWrite();
                //Toast.makeText(getActivity(), fileReader.dataRead[6], Toast.LENGTH_SHORT).show();
                break;
            // Close Title Screen Popup
            case R.id.button_back:
                titleScreenPop.dismiss();
                break;

            // Game Scores
            case R.id.options_02:

                break;
            // Game Modes
            case R.id.options_03:

                break;
            // Notifications
            case R.id.options_04:

                break;
            // Messages
            case R.id.options_05:

                break;
            // Graphics
            case R.id.options_06:

                break;
            // Bulletin
            case R.id.options_07:

                break;
            // User Support
            case R.id.options_08:
                // Enters the User Support Screen activity
                Intent i_0 = new Intent(getActivity(), UserSupportScreen.class);
                startActivity(i_0);
                break;
            // Connect
            case R.id.options_09:

                break;
            // Links
            case R.id.options_10:

                break;

            // Clear Data
            case R.id.options_11:
                // File Reading Set-Up
                fileReader = new AppStorage(context, "user-cp-s.cp");
                fileReader.dataRead();

                LayoutInflater inflater_2 = (LayoutInflater) getActivity().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout_2 = inflater_2.inflate(R.layout.popup_options_003,
                        (ViewGroup) v.findViewById(R.id.popup_options_003));
                clearDataPop = new PopupWindow(layout_2, fragOptionGraphics.getFullWidth(),
                        fragOptionGraphics.getFullHeight(), true);
                clearDataPop.showAtLocation(layout_2, Gravity.CENTER, 0, 0);
                editText_00 = (EditText) clearDataPop.getContentView().findViewById(R.id.editText_00);
                text_00 = (TextView) clearDataPop.getContentView().findViewById(R.id.screen_title);
                text_01 = (TextView) clearDataPop.getContentView().findViewById(R.id.text_00);
                text_02 = (TextView) clearDataPop.getContentView().findViewById(R.id.text_01);
                text_03 = (TextView) clearDataPop.getContentView().findViewById(R.id.text_02);
                text_00.setTypeface(font);
                text_01.setTypeface(font_1);
                text_02.setTypeface(font_1);
                text_03.setTypeface(font_1);
                button_00 = (Button) clearDataPop.getContentView().findViewById(R.id.button_data_cancel);
                button_01 = (Button) clearDataPop.getContentView().findViewById(R.id.button_data_clear);
                button_00.setTypeface(font_1);
                button_01.setTypeface(font_1);
                button_00.setOnClickListener(this);
                button_01.setOnClickListener(this);
                fragOptionGraphics.dimPopUpBackground(clearDataPop);
                break;
            // Close Data Clearing Popup
            case R.id.button_data_cancel:
                clearDataPop.dismiss();
                break;
            // Clear Data and Exit App
            case R.id.button_data_clear:
                String clearText = editText_00.getText().toString();
                // If the input text equals to the clear_key
                if(clearText.trim().equals(getResources().getString(R.string.clear_key))) {
                    fileReader.initializedData();
                    fileReader.dataWrite();
                    clearDataPop.dismiss();
                    getActivity().finish();
                    //System.exit(0);
                } else {
                    text_03.setText(R.string.clear_data_004);
                }
                break;
            default:
                break;
        }
    }

}
