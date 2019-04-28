package com.mycom.assignment2;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

class SensorInfo {
    float accX, accY, accZ;
    float ambTemp;
    float graX, graY, graZ;
    float gyrX, gyrY, gyrZ;
    float light;
    float laccX, laccY, laccZ;
    float magX, magY, magZ;
    float orX, orY, orZ;
    float pressure;
    float proximity;
    float humid;
    float rotX, rotY, rotZ;
    float temp;
}


public class MainActivity extends AppCompatActivity implements SensorEventListener{
    SensorManager sensorManager;
    SensorInfo sensor_info;
    static final float shake_threshold = 200;
    private long lastUpdate;
    private static boolean shown_dialog = false;
    int count=0;
    boolean selectOne=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensor_info = new SensorInfo();

    }

    public void showDialog(){
//        String message1 = String.format("AcceleroMeter (m/s^2): \n x: %1$s \n y: %2$s \n z: %3$s",
//                sensor_info.accX, sensor_info.accY, sensor_info.accZ);
//        TextView text1 = (TextView) findViewById(R.id.textView);
//        text1.setText(message1);
        Random r = new Random();
        int newnumber;
        if(!shown_dialog) {
            final AlertDialog.Builder viewDialog = new AlertDialog.Builder(this);
            int number = r.nextInt(20) + 1;
            viewDialog.setIcon(android.R.drawable.ic_btn_speak_now);
            viewDialog.setTitle("คุณได้หมายเลข"+String.valueOf(number));
            if(selectOne){
                viewDialog.setMessage("เลขที่"+String.valueOf(number)+" นโมพุทธายะ ชัยชนะโชคงามตามมาถึง มิตรที่โกรธโหดหื่นทำมึนตึง จะมาถึงเคหาไม่ช้าพลัน กับจะได้สิ่งของที่ต้องจิตต์ แน่ไม่ผิดตำราทายเหมือนหมายมั่น ถามถึงลูกผูกใจที่ในครรภ์ แน่สำคัญว่าเป็นหญิงเสียจริงจัง ถามหาที่พึ่งพาอยู่อาศัย ตอบว่าได้เสร็จสมอารมณ์หวัง อีกผู้ใหญ่ไปหาไม่กล้าชัง เป็นสมหวังโชคดีมีชัย เอยฯ\n");
                selectOne = false;
            }
            else{
                viewDialog.setMessage("เลขที่"+String.valueOf(number)+" ต้องเมื่อพระวิธูร แสนอาดูรยามวิโยคเฝ้าโศกศัลย์ เพราะพรหมทัตเล่นแพ้สกาพนัน จึงต้องส่งองค์ท่านให้ยักษ์ไป ใครเสี่ยงได้ใบนี้ที่จะยาก ต้องจรจากเคหาเคยอาศัย แม้มีจิตต์คิดการสิ่งใดๆ มักผิดได้ทั้งเนื้อคู่ไม่สู้ดี เป็นความค้างอย่างชนะเขา ได้ไม่เท่าเสียนักเสื่อมศักดิ์ศรี ต้องเสดาะเคราะห์กรรมกระทำพลี บุญนิธีจักช่วยอวยชัยเอย ฯ\n");
                selectOne = true;

            }
            viewDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    shown_dialog = false;
                    count=0;

                }
            });
            viewDialog.show();
        }
    }

    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                sensor_info.rotX = event.values[0];
                sensor_info.rotY = event.values[1];
                sensor_info.rotZ = event.values[2];
                float speed = Math.abs(sensor_info.rotX + sensor_info.rotY +sensor_info.rotZ - sensor_info.accX - sensor_info.accY - sensor_info.accZ) / diffTime * 10000;
                if (speed > shake_threshold) {
                   if(count==0){
                       count++;
                       showDialog();
                   }
                }
                sensor_info.accX = sensor_info.rotX;
                sensor_info.accY = sensor_info.rotY;
                sensor_info.accZ = sensor_info.rotZ;
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();


    }


}
