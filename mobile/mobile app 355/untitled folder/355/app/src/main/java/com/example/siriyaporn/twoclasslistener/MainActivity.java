package com.example.siriyaporn.twoclasslistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn1 = (Button) findViewById(R.id.button1);
        final Button btn2 = (Button) findViewById(R.id.button2);
        final Button btn3 = (Button) findViewById(R.id.button3);
        final EditText edit = (EditText) findViewById(R.id.edit);
        final TextView text2 = (TextView) findViewById(R.id.textView2);
        final TextView text3 = (TextView) findViewById(R.id.textView3);
        edit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    if(keyCode==KeyEvent.KEYCODE_ENTER){
                        text2.setText(text2.getText() + " | " + edit.getText());
                        edit.setText("");
                    }
                }
                return false;
            }
        });





        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text3.setText(new Date().toString() + " from button 1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text3.setText(new Date().toString() + " from button 2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text2.setText("");
                text3.setText("");
            }
        });
    }
}
