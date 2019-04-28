package com.mycom.schedule;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pkl.scheduler.R;

public class MainActivity extends AppCompatActivity {
    SampleAlarmReceiver alarm = new SampleAlarmReceiver();
    private  static Context mContext;

    public static Context getContext(){
        return mContext;
    }

    public  static void setContext(Context context){
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContext(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start_action:
                alarm.setAlarm(this);
                return true;
            case R.id.cancel_action:
                alarm.cancelAlarm(this);
                return true;
        }
        return false;
    }
}
