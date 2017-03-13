/*
Assignment #: Homework 07
File Name: MainActivity.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.hw7;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements GetNewsAsync.IData {

    String URL;
    ArrayList<Data> d;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int layout = 0;
    final static String PODCAST_KEY = "PODCAST";
    public ProgressBar pb;
    public ProgressBar pbpod;
    public static ArrayList<MediaPlayer> mp = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar ab =getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.mipmap.ic_ted);



        URL = "https://www.npr.org/rss/podcast.php?id=510298";
        new GetNewsAsync(MainActivity.this).execute(URL);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                if( layout == 0) {
                    mAdapter = new MyAdapterGrid(d, MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                    layout = 1;
                } else {
                    mAdapter = new MyAdapter(d, MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    layout = 0;
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupData(final ArrayList<Data> s) {


        d = s;
        for(int i = 0; i < 145; i++){
            if(s.get(i).getPublished_date().contains("2017")){
            s.remove(i);
            }
        }
        Log.d("SIZE",s.size()+"");
        //Collections.sort(s);
//        for(int i = 0; i < 50; i++){
//            int lowestDay = s.get(i).dateStrength;
//            int lowestIndex = i;
//            int temp = lowestDay;
//            int tempIndex = i;
//            //compare the date at i [0]
//            for(int j = i+1; j < 49; j++){
//
//                //Find the lowest value strenth and swap with i
//                if(s.get(j).dateStrength < temp){
//                    temp = s.get(j).dateStrength;
//                    tempIndex = j;
//                }
//            }
//            Log.d("POSITION ",(i+1)+"");
//            s.add(i,s.get(tempIndex));
//        }

        mRecyclerView = (RecyclerView) findViewById(R.id.podcast_rv);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Data podcast = s.get(position);

                if (mp.size() > 0) {
                    mp.get(0).stop();

                }

                //MyAdapter.aTask.cancel(true);

                Intent toPlayActivity = new Intent(MainActivity.this, PlayActivity.class);
                toPlayActivity.putExtra(PODCAST_KEY, podcast);

                startActivity(toPlayActivity);
            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(s, MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);

       pb = (ProgressBar) findViewById(R.id.pb_load);
        pbpod = (ProgressBar) findViewById(R.id.podcast_pb);
        //pbpod.setMax(200000);
        pb.setVisibility(GONE);
        mRecyclerView.setVisibility(VISIBLE);


    }

    public void updateProg(int b, int duration){

        pbpod.setProgress(b);


    }
}




