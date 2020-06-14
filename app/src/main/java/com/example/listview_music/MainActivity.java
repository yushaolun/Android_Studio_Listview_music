package com.example.listview_music;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {
    public MediaPlayer player= new MediaPlayer();
    public ListView listView;
    public LinkedList<Map<String, Object>> data=new LinkedList<Map<String, Object>>();
    public SimpleAdapter adapter;
    public int to[]={R.id.imageView,R.id.textView2};
    public ArrayList<String> arraylist=new ArrayList<String>();

    int image=R.drawable.ic_music;  //圖片
    public String string[]={"enemy","kiss_somebody","rare","soy_yo","start_again","undergo"}; //歌名
    public Field[]field;
    public boolean answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        field=R.raw.class.getFields();   //get raw資料夾內東西


        initview();  //呼叫初始化


        
    }

    public void initview(){  //初始化
        for (int i=0;i<field.length;i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("picture",image);
            listItem.put("song",string[i]);
            data.add(listItem);
            arraylist.add(field[i].getName());
        }



        adapter=new SimpleAdapter(this,data,R.layout.items,new String[]{"picture","song"},to); //設定listview
         listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //按下Listview 執行以下動作
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                player.release();   //釋放歌曲記憶體
                int res = getResources().getIdentifier(arraylist.get(i), "raw", getPackageName());  //get 哪一首歌
                player = MediaPlayer.create(MainActivity.this, res);
                player.setLooping(false); //不循環播放
                player.start();  //開始執行



            }
        });

    }


}
