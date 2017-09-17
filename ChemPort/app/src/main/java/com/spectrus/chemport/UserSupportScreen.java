package com.spectrus.chemport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class UserSupportScreen extends Activity {

    // Variables for the User Support activity
    Spinner subjectTopic;
    TextView title_text, text_00, text_01;
    Button button_00;
    EditText message_00;

    // Variables for Font resources
    public String fontPath, fontPath_1;
    public Typeface font, font_1;

    // Variables for AbMob advertising
    public AdView mAdView;
    public AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_support_screen);

        // Set up the font resources
        fontPath = "fonts/Rubik-Regular.ttf";
        fontPath_1 = "fonts/Titillium-Bold.ttf";
        font = Typeface.createFromAsset(this.getAssets(), fontPath);
        font_1 = Typeface.createFromAsset(this.getAssets(), fontPath_1);

        // Set up the activity graphics
        message_00 = (EditText) findViewById(R.id.subject_message);
        title_text = (TextView) findViewById(R.id.support_title);
        text_00 = (TextView) findViewById(R.id.text_00);
        text_01 = (TextView) findViewById(R.id.text_01);
        button_00 = (Button) findViewById(R.id.sendButton);
        title_text.setTypeface(font_1);
        text_00.setTypeface(font);
        text_01.setTypeface(font);
        button_00.setTypeface(font);

        // Initialize AdMob Ads
        MobileAds.initialize(getApplicationContext(), getString(R.string.app_ad_id));
        mAdView = (AdView) findViewById(R.id.baseAd);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Set up Drop List spinner
        subjectTopic = (Spinner) findViewById(R.id.subject_spinner);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> subjectAdapter = ArrayAdapter
                .createFromResource(this, R.array.support_subject_matter,
                        android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        subjectTopic.setAdapter(subjectAdapter);

    }

    // Sends the Mail Report from the user
    public void onMailSend(View v) {
        // Re-edit
        String toEmailAddress = getString(R.string.app_contact_email);
        String mSubject = getString(R.string.app_name) + ": " + subjectTopic.getSelectedItem().toString();
        String mMessage = message_00.getText().toString();

        Intent emailApp = new Intent(Intent.ACTION_SEND);
        emailApp.putExtra(Intent.EXTRA_EMAIL, new String[]{toEmailAddress});
        emailApp.putExtra(Intent.EXTRA_SUBJECT, mSubject);
        emailApp.putExtra(Intent.EXTRA_TEXT, mMessage);
        emailApp.setType("message/rfc822");
        startActivity(Intent.createChooser(emailApp, "Send Report With"));
        finish();
    }
}
