package com.mycom.autocomplete_radicheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AutoComplete
        AutoCompleteTextView textv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        String[] heroes = getResources().getStringArray(R.array.dccharactor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,heroes);
        textv.setAdapter(adapter);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.dccharactor,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        //Checkbox
    }
    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        final TextView txtView = (TextView)findViewById(R.id.checkboxtext);

        switch (view.getId()){
            case R.id.Cheese:
                if ((checked)) {
                    txtView.setText("Cheese");
                }else{
                    txtView.setText("No Cheese");
                }
                break;
            case R.id.Meat:
                if (checked){
                    txtView.setText("Meat");
                }else{
                    txtView.setText("No Meat");
                }
                break;
        }
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        final TextView txtview = (TextView)findViewById(R.id.radiotext);

        switch (view.getId()){
            case R.id.radio_pirates:
                if (checked)
                    txtview.setText("Pirates !");
                break;
            case R.id.radio_ninjas:
                if(checked)
                    txtview.setText("Ninjas !");
                break;
        }
    }
}
