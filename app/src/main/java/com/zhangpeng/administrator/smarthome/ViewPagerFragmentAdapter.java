package com.zhangpeng.administrator.smarthome;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> datas;
    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.datas=datas;
    }
    @Override
    public Fragment getItem(int position) {//返回子View对象
        return datas.get(position);
    }
    @Override
    public int getCount() {//返回子View的个数
        return datas.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {//初始子View方法
        return super.instantiateItem(container, position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//销毁子View
        super.destroyItem(container, position, object);
    }
}
