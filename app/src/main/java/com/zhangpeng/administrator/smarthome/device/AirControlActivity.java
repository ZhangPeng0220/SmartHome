package com.zhangpeng.administrator.smarthome.device;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.zhangpeng.administrator.smarthome.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AirControlActivity extends AppCompatActivity implements View.OnClickListener, AirMoveView.MoveInterface {
    private Button btnMode;
    private TextView tvValue;
    private AirMoveView moveView;
    private DownTimer timer;
    private TimePickerView pvTime;
    private TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.air_control);
        btnMode = (Button) findViewById(R.id.btn_mode);
        tvValue = (TextView) findViewById(R.id.tv_value);
        moveView = (AirMoveView) findViewById(R.id.move_view);
        moveView.setMoveInterface(this);
        btnMode.setOnClickListener(this);
        final TextView tv_scend = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        tv_scend.setText("总共三分钟");
        timer = new DownTimer();//实例化
        timer.setTotalTime(3*60*1000);//设置毫秒数
        timer.setIntervalTime(60*1000);//设置间隔数
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                Toast.makeText(AirControlActivity.this, "完成倒计时", Toast.LENGTH_SHORT).show();
                tv_scend.setText("点击设置时间");
            }

            @Override
            public void onInterval(long remainTime) {
                tv_scend.setText("还剩" + remainTime / 1000/60+"分钟就完成了");//剩余多少秒
            }
        });
    }

    int mode = 0;
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.start:
                timer.start();
                break;
            case R.id.pause:
                timer.pause();
                break;
            case R.id.cancel:
                timer.cancel();
                break;
            case R.id.resume:
                timer.resume();
                break;
            case R.id.text2:
                choose();
                break;
            case R.id. move_view:
                moveView.changeMode(mode);
                if (mode == 0) {
                    mode = 1;
                } else {
                    mode = 0;
                }
                break;
        }
    }

    @Override
    public void getCurrentDegrees(int degress) {
        tvValue.setText("当前度数：" + degress + "度");

    }
    public void choose(){
        pvTime = new TimePickerView(this, TimePickerView.Type.HOURS_MINS);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                text2.setText(getTime(date));
            }
        });
        pvTime.show();
    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
