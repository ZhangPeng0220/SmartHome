package com.zhangpeng.administrator.smarthome.device;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zhangpeng.administrator.smarthome.R;
import com.zhangpeng.administrator.smarthome.device.voice.VoiceActivity;

/**
 * Created by Administrator on 2016/8/9.
 */

    public class DeviceFragment extends Fragment {
    private FragmentManager manager;
    private FragmentTransaction ft;
    private Button button;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.device_tab, container, false);
            ListView list=(ListView)view.findViewById(R.id.listview);
            button = (Button)view.findViewById(R.id.voice);
            MyAdapter adapter=new MyAdapter(getContext());
            list.setAdapter(adapter);
            manager = getFragmentManager();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), VoiceActivity.class);
                    startActivity(intent);
                }
            });
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String result = String.valueOf(position);
                    if(position == 1){
                        Intent intent = new Intent(getContext(),AirControlActivity.class);
                        startActivity(intent);
                    }


                }
            });
            return view;
        }

    }

