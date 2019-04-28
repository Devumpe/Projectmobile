package com.mycom.schedule;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;
import java.util.Calendar;

public class SampleAlarmReceiver extends WakefulBroadcastReceiver {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, SampleSchedulingService.class);
        startWakefulService(context, service);
    }

    public void setAlarm(Context context) {
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SampleAlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // (1) Wake up the device to fire a one-time alarm in one minute.
        /*
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                     SystemClock.elapsedRealtime() + 60*1000, alarmIntent);
        */
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 5 * 1000, alarmIntent);

        // (2) Wake up the device to fire the alarm in 30 minutes, and every 30 minutes after that.
        /*
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                     AlarmManager.INTERVAL_HALF_HOUR, AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);
        */

        // (3) Set the alarm to fire at approximately 8:30 a.m., according to the device's clock,
        // and to repeat once a day.
        /*
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // Set the alarm's trigger time to 8:30 a.m.
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                                     calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
        */
        Toast.makeText(context, "Set Alarm", Toast.LENGTH_LONG).show();

        // Enable {@code SampleBootReceiver} to automatically restart the alarm when the
        // device is rebooted.
        ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void cancelAlarm(Context context) {
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }
        Toast.makeText(context, "Cancel Alarm", Toast.LENGTH_LONG).show();

        // Disable {@code SampleBootReceiver} so that it doesn't automatically restart the
        // alarm when the device is rebooted.
        ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}