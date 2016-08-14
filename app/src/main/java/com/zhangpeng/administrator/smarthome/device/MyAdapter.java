package com.zhangpeng.administrator.smarthome.device;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangpeng.administrator.smarthome.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/10.
 */

public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;
    public static Map<Integer, Boolean> isSelected;

    public MyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        init();
    }

    //初始化
    private void init() {
        mData=new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("img", R.drawable.splash);
        map.put("title", "台灯");
        mData.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.drawable.splash);
        map.put("title", "门锁");
        mData.add(map);

        map.put("img", R.drawable.splash);
        map.put("title", "空调");
        mData.add(map);



    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //convertView为null的时候初始化convertView。
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img.setBackgroundResource((Integer) mData.get(position).get(
                "img"));
        holder.title.setText(mData.get(position).get("title").toString());

        return convertView;
    }

    public final class ViewHolder {
        public ImageView img;
        public TextView title;

    }
}

