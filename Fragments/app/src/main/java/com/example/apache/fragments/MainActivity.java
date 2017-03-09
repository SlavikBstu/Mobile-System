package com.example.apache.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private Timer mTimer;
    private MyTimerTask mMyTimerTask;

    TextView time;
    Fragment fragment1, fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            fragment1 = new FragmentOne();
            FragmentManager manager1 = getFragmentManager();
            FragmentTransaction transaction1 = manager1.beginTransaction();
            transaction1.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            time = (TextView)findViewById(R.id.Timer);
            fragment2 = new FragmentTwo();
            FragmentManager manager2 = getFragmentManager();
            FragmentTransaction transaction2 = manager2.beginTransaction();
            transaction2.commit();
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
            }
            mTimer = new Timer();
            mMyTimerTask = new MyTimerTask();

                // delay 1000ms, repeat in 5000ms
                mTimer.schedule(mMyTimerTask, 1000, 1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void change(View view){
        BounceView bounce = new BounceView(this);
        setContentView(bounce);
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "dd:MMMM:yyyy HH:mm:ss a", Locale.getDefault());
            final String strDate = simpleDateFormat.format(calendar.getTime());

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    time.setText(strDate);
                }
            });
        }
    }
}
