package com.example.pkl.scheduler;

import android.app.AlertDialog;
import android.app.IntentService;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.os.PowerManager;
import android.widget.Toast;

public class SampleSchedulingService extends IntentService {
    public SampleSchedulingService() {
        super("SchedulingService");
    }
//    public static final int NOTIFICATION_ID = 1;
//    String CHANNEL_ID = "1";
//    private NotificationManager mNotificationManager;
//    final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification("Hello");
        SampleAlarmReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg) {
//        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,new Intent(this, MainActivity.class), 0);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(getString(R.string.header))
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(msg))
//                .setContentText(msg);
//        mBuilder.setContentIntent(contentIntent);
//        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.getContext());
                dialog.setTitle("Scheduling");
                dialog.setIcon(android.R.drawable.btn_star_big_on);
                dialog.setMessage("ปูเป้ สิริญาพร :)");
                dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SampleSchedulingService.this,"Close",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("Scheduler");
        keyguardLock.disableKeyguard();
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "Scheduler");
        wakeLock.acquire();
    }
}