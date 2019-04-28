package com.mycom.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable mAnimation;
    ImageView img;
    int x=0,y=0;
    private MotionEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.pic);

        BitmapDrawable[] frame = new BitmapDrawable[21];
        int i=0;
        for(i=1; i<=20; i++){
            frame[i] = (BitmapDrawable)getResources().getDrawable(
                    getResources().getIdentifier((String)"cat"+i, "drawable", this.getPackageName()) );
        }
        int reasonableDuration = 100;
        mAnimation = new AnimationDrawable();
        for(i=1; i<=20; i++){
            mAnimation.addFrame(frame[i], reasonableDuration);
        }
        img.setImageDrawable(mAnimation);

        //Auto Move
//        TranslateAnimation tAnimation = new TranslateAnimation(0, 260, 0, 0);
//        tAnimation.setDuration(5000);
//        tAnimation.setFillAfter(true);
//        tAnimation.setRepeatCount(Animation.INFINITE);
//        tAnimation.setRepeatMode(Animation.REVERSE);
//        img.setAnimation(tAnimation);

        //Image Rotation
//        RotateAnimation rAnim = new RotateAnimation(0f, 180f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        rAnim.setStartOffset(0);
//        rAnim.setDuration(10000);
//        rAnim.setFillAfter(true);
//        rAnim.setRepeatCount(Animation.INFINITE);
//        rAnim.setRepeatMode(Animation.RESTART);
//        img.setAnimation(rAnim);

        //Image Scale
//        ScaleAnimation sAnimation = new ScaleAnimation(1f, 2f, 1f, 2f,
//                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        sAnimation.setDuration(10000);
//        sAnimation.setFillAfter(true);
//        sAnimation.setRepeatCount(Animation.INFINITE);
//        sAnimation.setRepeatMode(Animation.RESTART);
//        img.setAnimation(sAnimation);

        final Button startbtn = (Button) findViewById(R.id.startbut);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.start();
                mAnimation.setOneShot(false);
            }
        });

        final Button stpbtn = (Button) findViewById(R.id.stopbut);
        stpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.stop();
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Drawable d = getResources().getDrawable(R.drawable.cat2);
            int h = d.getIntrinsicHeight();
            int w = d.getIntrinsicWidth();

            TranslateAnimation tAnimation =
                    new TranslateAnimation(x, ((int)event.getX()-(w)),
                            y, ((int)event.getY()-h));
            x = (int)event.getX()-(w);
            y = (int)event.getY()-h;
            tAnimation.setDuration(1000);
            tAnimation.setFillAfter(true);
            tAnimation.setRepeatCount(0);
            tAnimation.setRepeatMode(Animation.RESTART);
            img.startAnimation(tAnimation);
            return true;
        }
        return super.onTouchEvent(event);
    }
}


