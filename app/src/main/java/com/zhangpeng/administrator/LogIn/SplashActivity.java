package com.zhangpeng.administrator.LogIn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.zhangpeng.administrator.smarthome.MainActivity;
import com.zhangpeng.administrator.smarthome.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash);
        final Handler handler=new Handler();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                Intent intent = new Intent(SplashActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
                //handler.postDelayed(this, 2000);

            }
        };
        handler.postDelayed(runnable, 2000);//延时跳转功能


    }

}
