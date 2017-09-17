package com.spectrus.chemport;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.spectrus.chemport.AppData.ElementData;
import com.spectrus.chemport.AppFunction.AppGraphics;

public class PeriodicTableScreen extends Activity {

    // Variable for the Periodic Table activity
    ElementData ptData;
    AppGraphics ptGraphics;
    Context context;

    TextView text_00;
    ListView listView_00;
    public String [] elementListing;

    // Popup variables
    PopupWindow elementPop;
    TextView pt_00, pt_01, pt_02, pt_03, pt_04;
    Button button_00;

    // Variables for Font resources
    public String fontPath, fontPath_1;
    Typeface font, font_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_table_screen);
        context = PeriodicTableScreen.this;
        ptData = new ElementData();
        ptGraphics = new AppGraphics(context);

        // Start the periodic table listing
        elementListing = new String [ptData.element_labels.length - 1];
        for(int index = 1; index < ptData.element_labels.length; index++) {
            elementListing[index - 1] = index + ". " + ptData.element_labels[index][1];
        }

        // Set up the font resources
        fontPath = "fonts/Rubik-Regular.ttf";
        fontPath_1 = "fonts/Titillium-Bold.ttf";
        font = Typeface.createFromAsset(this.getAssets(), fontPath);
        font_1 = Typeface.createFromAsset(this.getAssets(), fontPath_1);

        // Set up the activity graphics
        text_00 = (TextView) findViewById(R.id.periodic_title);
        text_00.setTypeface(font_1);

        // Start ArrayAdapter and ListView
        listView_00 = (ListView)findViewById(R.id.listView_00);
        ArrayAdapter<String> adapter_00 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,
                android.R.id.text1, elementListing);
        listView_00.setAdapter(adapter_00);

        // ListView setOnItemClickListener function
        listView_00.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(PeriodicTableScreen.this, elementListing[position], Toast.LENGTH_SHORT).show();

                // Set up Element data Popup
                LayoutInflater inflater_0 = (LayoutInflater) context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout_0 = inflater_0.inflate(R.layout.popup_index_001,
                        (ViewGroup) view.findViewById(R.id.popup_index_001));
                elementPop = new PopupWindow(layout_0, ptGraphics.getFullWidth(),
                        ptGraphics.getFullHeight(), true);
                elementPop.showAtLocation(layout_0, Gravity.CENTER, 0, 0);
                pt_00 = (TextView) elementPop.getContentView().findViewById(R.id.element_title);
                pt_01 = (TextView) elementPop.getContentView().findViewById(R.id.text_00);
                pt_02 = (TextView) elementPop.getContentView().findViewById(R.id.text_01);
                pt_03 = (TextView) elementPop.getContentView().findViewById(R.id.text_02);
                pt_04 = (TextView) elementPop.getContentView().findViewById(R.id.text_03);
                pt_00.setTypeface(font_1);
                pt_01.setTypeface(font);
                pt_02.setTypeface(font);
                pt_03.setTypeface(font);
                pt_04.setTypeface(font);
                // Set Up Information
                pt_00.setText(ptData.element_labels[position+1][1]);
                pt_01.setText(ptData.element_labels[position+1][1] + " (" + ptData.element_labels[position+1][0] + ")");
                pt_02.setText(getString(R.string.periodic_001) + " " + (position+1));
                pt_03.setText(getString(R.string.periodic_002) + " " + ptData.element_list[position+1][1]);
                pt_04.setText(getString(R.string.periodic_003));
                button_00 = (Button) elementPop.getContentView().findViewById(R.id.per_close);
                button_00.setTypeface(font);
                ptGraphics.dimPopUpBackground(elementPop);
            }
        });
    }

    // Close Button in the Element Data Popup
    public void onCloseElement(View v){
        elementPop.dismiss();
    }
}
