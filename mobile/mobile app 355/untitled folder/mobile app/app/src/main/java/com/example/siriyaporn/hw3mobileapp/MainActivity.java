package com.example.siriyaporn.hw3mobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText input = (EditText) findViewById(R.id.edit_input);
        final Button btn = (Button) findViewById(R.id.btn_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                String topass = input.getText().toString();
                intent.putExtra("frompass", topass);
                startActivity(intent);
            }
        });
    }
}
