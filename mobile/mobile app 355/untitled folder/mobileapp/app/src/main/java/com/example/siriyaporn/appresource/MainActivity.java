package com.example.siriyaporn.appresource;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Resources res = getResources();
         final String[] array = res.getStringArray(R.array.heroes);
         final TextView txt1 = (TextView) findViewById(R.id.textView1);
         txt1.setText(array[1]);
         int color = res.getColor(R.color.opaque_red);
        float fontSize = res.getDimension(R.dimen.normal_size);
    }
}
