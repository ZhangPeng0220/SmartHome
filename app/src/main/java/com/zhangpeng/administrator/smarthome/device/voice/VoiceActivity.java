package com.zhangpeng.administrator.smarthome.device.voice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.zhangpeng.administrator.smarthome.R;

public class VoiceActivity extends AppCompatActivity {

    private Button speak;
    private Button stop;
    private TextView show;
    private SpeechRecognizer mIat;
    private String TAG = "VOICE";
    private StringBuilder result;
    private KqwUnderstander mKqwUnderstander;
    private TextView mTvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice);
        speak = (Button)findViewById(R.id.speak);
        stop = (Button)findViewById(R.id.stop);
        show = (TextView)findViewById(R.id.voice_show);
        SpeechUtility.createUtility(this, "appid=57aae8c3 ");
        mIat = SpeechRecognizer.createRecognizer(this, mInitListener);
        mTvShow = (TextView) findViewById(R.id.tv_show);

        // 初始化语音合成对象
        mKqwUnderstander = new KqwUnderstander(this) {
            @Override
            public void result(String json) {
                mTvShow.setText(json);
                // Toast.makeText(MainActivity.this, "json = " + json, Toast.LENGTH_SHORT).show();
            }
        };
    }
    public void speak(View view){
        result = new StringBuilder();
        setParam();
        int ret = mIat.startListening(recognizerListener);
        Log.d(TAG, "startListening ret:"+ret);
        /*RecognizerDialog    iatDialog = new RecognizerDialog(this,mInitListener);
        //2.设置听写参数，同上节
        //3.设置回调接口
        iatDialog.setListener(mRecognizerDialogListener);
        //4.开始听写
        iatDialog.show();*/
    }
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
        }

    };

    public void setParam(){
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // ������������
        mIat.setParameter(SpeechConstant.ACCENT,"mandarin");
    }

    /**
     * ��д��������
     */
    private RecognizerListener recognizerListener=new RecognizerListener(){

        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        public void onBeginOfSpeech() {
            show.setText(null);
        }


        @Override
        public void onError(SpeechError error) {
        }

        @Override
        public void onEndOfSpeech() {
        }



        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            result.append(JsonParser.parseIatResult(results.getResultString()));
            if (isLast){
                show.setText(result);
            }

        }




        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
        }
    };
    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG,results.getResultString());

        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            Log.d(TAG,"ERROR");

        }

    };
    public void start(View view) {
        Toast.makeText(this, "语义理解 : " + show.getText().toString().trim(), Toast.LENGTH_SHORT).show();
        mKqwUnderstander.textUnderstander(show.getText().toString().trim());
    }
    public void stop(View view){
        if (mIat.isListening())
        {
            mIat.stopListening();
        }
    }

}
