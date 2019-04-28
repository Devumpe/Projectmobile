package com.mycom.mobileproject;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.Calendar;
import static android.provider.BaseColumns._ID;
import static com.mycom.mobileproject.Constants.BALANCE;
import static com.mycom.mobileproject.Constants.SPENT;
import static com.mycom.mobileproject.Constants.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    int day, month, year;
    private MoneysData moneys;


    public EditText input1;
    public EditText input2;
    public EditText input3;
    public ImageButton submit;
    public Button showdatebtn;
    public Button btnmonth;
    public TextView txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = (EditText) findViewById(R.id.edittotal);
        input2 = (EditText) findViewById(R.id.editspent);
        input3 = (EditText) findViewById(R.id.editbalance);
        submit = (ImageButton) findViewById(R.id.submit);
        showdatebtn = (Button) findViewById(R.id.showdatebtn);
        btnmonth = (Button) findViewById(R.id.weekbtn);
        txtDate = (TextView) findViewById(R.id.showdate);
        final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


        btnmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                String total = input1.getText().toString();
                String spent = input2.getText().toString();
                String balance = input3.getText().toString();


                intent.putExtra("fromtotal", total);
                intent.putExtra("fromspent", spent);
                intent.putExtra("frombalance", balance);

                startActivity(intent);

            }


        });
        showdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                Calendar cld = Calendar.getInstance();
                year = cld.get(Calendar.YEAR);
                month = cld.get(Calendar.MONTH);
                day = cld.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + " - " + MONTHS[(month + 1)] + " - " + year);
                    }
                }, year, month, day);

                datePickerDialog.show();

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneys = new MoneysData(MainActivity.this);
                try {
                    addEvent();

                } finally {
                    moneys.close();
                }
            }
        });
    }

    private void addEvent() {
        String balan = input1.getText().toString();
        String sp = input2.getText().toString();
        SQLiteDatabase db = moneys.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BALANCE, balan);
        values.put(SPENT, sp);
        db.insert(TABLE_NAME, null, values);
    }
}



