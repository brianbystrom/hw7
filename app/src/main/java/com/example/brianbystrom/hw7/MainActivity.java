package com.example.brianbystrom.hw7;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Parcelable;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);



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

        for (int i = 0; i > s.size(); i++) {
            Log.d("DEMO", s.get(i).getTitle());
        }

        d = s;

        mRecyclerView = (RecyclerView) findViewById(R.id.podcast_rv);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Data podcast = s.get(position);
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

        ProgressBar pb = (ProgressBar) findViewById(R.id.pb_load);
        pb.setVisibility(GONE);
        mRecyclerView.setVisibility(VISIBLE);


    }
}




