package com.example.admin.music;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnPlay,btnPause,btnStop;

    ListView listView;

    List<String> list;

    ListAdapter adapter;

    MediaPlayer mediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay= (Button)findViewById(R.id.btnPlay);
        Button btnPause= (Button)findViewById(R.id.btnPause);
        Button btnStop= (Button)findViewById(R.id.btnStop);



        listView= (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();

        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++){
            list.add(fields[i].getName());
        }

        //remove first two elements
        list.remove(0);
        list.remove(8);


        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if (mediaplayer!= null){
                    mediaplayer.release();
                }
               int resId = getResources().getIdentifier(list.get(1),"raw",getPackageName());
               mediaplayer= MediaPlayer.create(MainActivity.this,resId);
                mediaplayer.start();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"pause",Toast.LENGTH_SHORT).show();
                mediaplayer.pause();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.stop();
                MainActivity.this.finish();
            }
        });


    }
}
