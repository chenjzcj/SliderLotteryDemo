package com.spring.slider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    private SlyderView slyderView;
    private Random mRandom;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //停止在那个位置
            slyderView.stop(mRandom.nextInt(6));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slyderView = new SlyderView(this);
        setContentView(slyderView);
        slyderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!slyderView.isRunning()) {
                    slyderView.play();
                } else {
                    Toast.makeText(MainActivity.this, "还没有完成哦", Toast.LENGTH_LONG).show();
                }
            }
        });

        mRandom = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5 * 1000);
                handler.sendEmptyMessage(1);
            }
        }).start();
    }
}
