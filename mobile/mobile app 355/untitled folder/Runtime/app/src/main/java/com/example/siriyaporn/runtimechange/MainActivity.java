package com.example.siriyaporn.runtimechange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.widget.TextView;
import  android.content.res.Configuration;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                final TextView txtView=(TextView)findViewById(R.id.textView);
                txtView.setText("Start");
        }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        final TextView txtView = (TextView)findViewById(R.id.textView);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            txtView.setText(txtView.getText() + " landscape");
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            txtView.setText(txtView.getText() + " portrait");
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();

        }

    }

}
