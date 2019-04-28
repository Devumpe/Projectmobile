package com.example.siriyaporn.hw3mobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
public class MainActivity2 extends AppCompatActivity {




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            final TextView msg = (TextView) findViewById(R.id.TextView_msg);
            String frompass = getIntent().getStringExtra("frompass");
            msg.setText(frompass);
        }
    }
