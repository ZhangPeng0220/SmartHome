package com.zhangpeng.administrator.smarthome.scene;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhangpeng.administrator.smarthome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */

public class MyDialog extends Dialog implements AdapterView.OnItemClickListener {
    private EditText mEditText;
    private TextView sure,cancel;
    private SettingListener mSListener;
    private String model = null ;
    private PopupWindow mPopup;
    private PopupWindow muPopup;
    private PopupWindow ligPopup;// 点击图片弹出popupwindow
    private WrapListView mPopView; // popupwindow的布局
    private Context mContext;
    private ImageButton muchoice,ligchoice;
    private EditText musicEdit,lightEdit,name;
    public MyDialog(Context context) {
        super(context);
        this.mContext = context;
    }
    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        mPopView = (WrapListView) LayoutInflater.from(mContext).inflate(R.layout.pop_view, null);
        musicEdit = (EditText) findViewById(R.id.musicEdit);
        lightEdit = (EditText) findViewById(R.id.lightEdit);
        muchoice = (ImageButton) findViewById(R.id.muchoice);
        name = (EditText) findViewById(R.id.nameEdit);
        muchoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mushow(muchoice);
            }
        });
        ligchoice = (ImageButton) findViewById(R.id.ligchoice);
        ligchoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ligshow(ligchoice);
            }
        });
        sure = (TextView) findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makesure();
            }
        });
        cancel = (TextView) findViewById(R.id.cancel);
    }
    public void musetAdapter(BaseAdapter muadapter) {
        muPopup = new PopupWindow(mPopView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        muPopup.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        muPopup.setFocusable(true); // 让popwin获取焦点
        mPopView.setAdapter(muadapter);
        mEditText = musicEdit;
        mPopup = muPopup;
        mPopView.setOnItemClickListener(this);

    }
    public void ligsetAdapter(BaseAdapter ligadapter){
        ligPopup = new PopupWindow(mPopView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ligPopup.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        ligPopup.setFocusable(true); // 让popwin获取焦点
        mPopView.setAdapter(ligadapter);
        mEditText = lightEdit;
        mPopup = ligPopup;
        mPopView.setOnItemClickListener(this);
    }
    public void mushow(View view){
        musetAdapter(new BaseAdapter() {
            private List<String> mList = new ArrayList<String>() {
                {
                    add("起床");
                    add("赖床");
                    add("浪漫");
                    add("孩童");
                    add("晚餐");

                }
            };


            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int position) {
                return mList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(mContext);
                tv.setText(mList.get(position));
                return tv;
            }
        });
        if(muPopup.isShowing()) {
            muPopup.dismiss();

            return;
        }

        muPopup.showAsDropDown(view, -225,5);
    }
    public void ligshow(View view){
        ligsetAdapter(new BaseAdapter() {
            private List<String> mList = new ArrayList<String>() {
                {
                    add("柔和");
                    add("高亮");
                    add("全关");

                }
            };


            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int position) {
                return mList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(mContext);
                tv.setText(mList.get(position));
                return tv;
            }
        });
        if(ligPopup.isShowing()) {
            ligPopup.dismiss();
            return;
        }

        ligPopup.showAsDropDown(view, -225,5);
    }
    public void makesure(){
        if (mSListener != null) {
            mSListener.onSetting(name.getText().toString());
        }
        this.dismiss();
    }
    public String getModel(){
        return model;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mEditText.setText(mPopView.getAdapter().getItem(i).toString());
        mPopup.dismiss();
    }
    public void setOnSettingListener(SettingListener listener) {
        mSListener = listener;
    }
}
