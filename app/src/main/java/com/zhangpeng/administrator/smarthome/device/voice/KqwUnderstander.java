package com.zhangpeng.administrator.smarthome.device.voice;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.TextUnderstander;
import com.iflytek.cloud.TextUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;

/**
 * Created by Administrator on 2016/8/13.
 */

public abstract class KqwUnderstander {

    public abstract void result(String json);

    // 上下文
    private Context mContext;
    // Log标记
    private static final String TAG = "KqwUnderstander";
    // 语义理解对象（文本到语义）。
    private TextUnderstander mTextUnderstander;

    /**
     * 构造方法
     *
     * @param context
     */
    public KqwUnderstander(Context context) {
        // 上下文
        mContext = context;

        // 初始化语义理解对象
        mTextUnderstander = TextUnderstander.createTextUnderstander(context, textUnderstanderListener);
    }

    // TODO  返回科大讯飞返回Json的实体类的对象
    public String textUnderstander(String text) {
        if (mTextUnderstander.isUnderstanding()) {
            mTextUnderstander.cancel();
        } else {
            int ret = mTextUnderstander.understandText(text, textListener);
            if (ret != 0) {
                Toast.makeText(mContext, "语义理解失败,错误码:" + ret, Toast.LENGTH_SHORT).show();
            }
        }

        return null;
    }

    /**
     * 初始化监听器（文本到语义）。
     */
    private InitListener textUnderstanderListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "textUnderstanderListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(mContext, "初始化失败,错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private TextUnderstanderListener textListener = new TextUnderstanderListener() {

        @Override
        public void onResult(final UnderstanderResult result) {

            if (null != result) {
                // 显示
                Log.d(TAG, "understander result：" + result.getResultString());
                // Toast.makeText(mContext, "understander result：" + result.getResultString(), Toast.LENGTH_SHORT).show();
                String text = result.getResultString();
                if (!TextUtils.isEmpty(text)) {
                    result(text);
                }
            } else {
                Log.d(TAG, "understander result:null");
                Toast.makeText(mContext, "识别结果不正确。", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onError(SpeechError error) {
            Toast.makeText(mContext, "onError Code：" + error.getErrorCode(), Toast.LENGTH_SHORT).show();
        }
    };
}
