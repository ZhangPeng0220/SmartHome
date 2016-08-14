package com.zhangpeng.administrator.smarthome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhangpeng.administrator.smarthome.device.DeviceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //主要要继承自FragmentActivity,这样才能在初始适配器类是使用getSupportFragmentManager方法获取FragmentManager对象
    private ViewPager mViewPager;
    private List<Fragment> datas;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private LinearLayout mLLChat,mLLFrd,mLLFind,mLLMe;
    private ImageView mImageViewChat,mImageViewFrd,mImageViewFind,mImageViewMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initDatas();//初始化数据集
        initView();// 初始化控件
        initEvent();// 注册单击监听
        viewPagerFragmentAdapter=new ViewPagerFragmentAdapter(getSupportFragmentManager(),datas);//初始化适配器类
        mViewPager.setAdapter(viewPagerFragmentAdapter);
    }
    private void initDatas() {
        datas=new ArrayList<Fragment>();
        datas.add(new DeviceFragment());
        datas.add(new MyFragment2());
        datas.add(new MyFragment3());
        datas.add(new MyFragment4());

    }
    private void initEvent() {
        mLLChat.setOnClickListener(this);
        mLLFrd.setOnClickListener(this);
        mLLFind.setOnClickListener(this);
        mLLMe.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {//ViewPager滑动切换监听
            @Override
            public void onPageSelected(int arg0) {
                int currentItem=mViewPager.getCurrentItem();
                resetImag();
                switch (currentItem) {
                    case 0:
                        mImageViewChat.setImageResource(R.drawable.splash);
                        break;
                    case 1:
                        mImageViewFrd.setImageResource(R.drawable.splash);
                        break;
                    case 2:
                        mImageViewFind.setImageResource(R.drawable.splash);
                        break;
                    case 3:
                        mImageViewMe.setImageResource(R.drawable.splash);
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mLLChat = (LinearLayout) findViewById(R.id.ll_chat);
        mLLFrd = (LinearLayout) findViewById(R.id.ll_frd);
        mLLFind = (LinearLayout) findViewById(R.id.ll_find);
        mLLMe = (LinearLayout) findViewById(R.id.ll_me);
        mImageViewChat = (ImageView) findViewById(R.id.img_chat);
        mImageViewFrd = (ImageView) findViewById(R.id.img_frd);
        mImageViewFind = (ImageView) findViewById(R.id.img_find);
        mImageViewMe = (ImageView) findViewById(R.id.img_me);
    }
    @Override
    public void onClick(View v) {
        resetImag();
        switch (v.getId()) {
            case R.id.ll_chat:
                mViewPager.setCurrentItem(0);
                mImageViewChat.setImageResource(R.drawable.splash);
                break;
            case R.id.ll_frd:
                mViewPager.setCurrentItem(1);
                mImageViewFrd.setImageResource(R.drawable.splash);
                break;
            case R.id.ll_find:
                mViewPager.setCurrentItem(2);
                mImageViewFind.setImageResource(R.drawable.splash);
                break;
            case R.id.ll_me:
                mViewPager.setCurrentItem(3);
                mImageViewMe.setImageResource(R.drawable.splash);
                break;
            default:
                break;
        }
    }
    private void resetImag() {//重置图片
        mImageViewChat.setImageResource(R.drawable.splash_no);
        mImageViewFrd.setImageResource(R.drawable.splash_no);
        mImageViewFind.setImageResource(R.drawable.splash_no);
        mImageViewMe.setImageResource(R.drawable.splash_no);
    }
}
