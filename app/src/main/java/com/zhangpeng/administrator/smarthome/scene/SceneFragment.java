package com.zhangpeng.administrator.smarthome.scene;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.zhangpeng.administrator.smarthome.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/9.
 */

public class SceneFragment extends Fragment implements SettingListener {
    private GridView grid_test;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpAdapter;
    private Map<String, Object> map;
    private MyDialog dialog;
    private int[] img = {R.mipmap.home, R.mipmap.home1,
            R.mipmap.home2, R.mipmap.home3};
    private List<String> imgName = new ArrayList<>();
    private ImageView add;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scene, container, false);
        add = (ImageView) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        grid_test = (GridView) view.findViewById(R.id.grid_test); // step1
        dataList = new ArrayList<Map<String, Object>>(); // step2
        simpAdapter = new SimpleAdapter(getContext(), getData(), R.layout.grid_item,
                new String[]{"img", "txt"}, new int[]{R.id.img_item, R.id.txt_item});
        grid_test.setAdapter(simpAdapter); // step3
        return view;
    }
    private List<Map<String, Object>> getData() {
        imgName.add("下班状态");
        imgName.add("起床");
        imgName.add("午睡");
        imgName.add("晚餐");
        String[] array=new String[imgName.size()];
        for(int i=0;i<imgName.size();i++){
            array[i]=(String)imgName.get(i);
            Log.d("nihao",array[i]);
        }
        for (int i=0; i<imgName.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("img", img[2]);
            map.put("txt", array[i]);
            dataList.add(map);
        }
        Iterator it = imgName.iterator();

        return dataList;
    }

    @Override
    public void onSetting(String name) {
        imgName.add(name);
    }
    public void add(){
        dialog = new MyDialog(getContext());
        dialog.setTitle("自定义样式");
        dialog.setOnSettingListener(this);
        dialog.show();
    }
}
