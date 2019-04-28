package com.mycom.mobileproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    public Button btntoday ;
    public TextView msgtotal ;
    public String fromtotal ;
    public TextView msgspent ;
    public String fromspent ;
    public TextView msgbalance ;
    public String frombalance ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         btntoday = (Button) findViewById(R.id.todaybtn);
         msgtotal = (TextView) findViewById(R.id.edittotal);
         fromtotal = getIntent().getStringExtra("fromtotal");
         msgspent = (TextView) findViewById(R.id.editspent);
         fromspent = getIntent().getStringExtra("fromspent");
         msgbalance = (TextView) findViewById(R.id.editbalance);
         frombalance = getIntent().getStringExtra("frombalance");


        msgtotal.setText(fromtotal);
        msgspent.setText(fromspent);
        msgbalance.setText(frombalance);


        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);

        btntoday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
