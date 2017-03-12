package com.example.brianbystrom.hw7;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class PlayActivity extends AppCompatActivity implements PlayPodcastAsync.IData {

    TextView title, description, duration, pubDate;
    ImageView image;
    ImageButton control;
    ProgressBar progress;
    boolean play = false;
    ProgressBar podcastBar;
    boolean hasPlayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        title = (TextView) findViewById(R.id.pc_title);
        description = (TextView) findViewById(R.id.pc_description);
        duration = (TextView) findViewById(R.id.pc_duration);
        pubDate = (TextView) findViewById(R.id.pc_pubdate);
        image = (ImageView) findViewById(R.id.pc_image);
        control = (ImageButton) findViewById(R.id.pc_control);
       // progress = (ProgressBar) findViewById(R.id.pc_progressbar);
        podcastBar = (ProgressBar) findViewById(R.id.pc_progressbar);
        //podcastBar.setMax(10);
        if(getIntent().getExtras() != null) {
            final Data podcast = (Data) getIntent().getExtras().getParcelable(MainActivity.PODCAST_KEY);
            //name_input.setText((String) editableMovie.name);
            Log.d("HL", "" + podcast.getUrlToMp3());
            title.setText(podcast.getTitle());
            description.setText("Description: " + podcast.getDescription());
            pubDate.setText("Published Date: " + podcast.getPublished_date());
            duration.setText("Duration: " + podcast.getDuration() + " seconds");


            if (!podcast.getUrlToImage().toString().equals("")) {
                Picasso.with(PlayActivity.this).load(podcast.getUrlToImage()).into(image);

            }

            control.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!hasPlayed) {
                        hasPlayed = true;
                        new PlayPodcastAsync(PlayActivity.this).execute(podcast.getUrlToMp3());
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void playPodcast(final MediaPlayer mPlayer) {
        mPlayer.start();
        //play = true;
        new syncTimeA(mPlayer,PlayActivity.this).execute(mPlayer);


        control.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));

        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play) {
                    control.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
                    mPlayer.pause();
                    play = false;
                } else {
                    control.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
                    mPlayer.start();
                    play = true;
                }
            }
        });


    }
    public void updateProg(int b){
        Log.d("HIT ME BABY","ONE MORE TIME");
        podcastBar.setProgress(b);


    }

    public class syncTimeA extends AsyncTask<MediaPlayer,Void,Void> {
        MediaPlayer mPlayer;
        Context playActivity;
        public syncTimeA(MediaPlayer b, Context p) {
            super();
            playActivity = p;
            mPlayer = b;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((PlayActivity) playActivity).podcastBar.setMax(mPlayer.getDuration());
            //Log.d("HIT ME BABY","ONE MORE TIME", ((PlayActivity) playActivity).podcastBar.setMax(mPlayer.getDuration()));

//            try{
//                for(;;){
//                    Thread.sleep(1000);
//                    counter++;
//                    Log.d("CURRENT",counter+"");
//                    p.setProgress(counter);
//                }}
//            catch (Exception e){
//
//            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(MediaPlayer... progressBars) {
           // Log.d("Duration","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            int duration = mPlayer.getDuration();
            while(mPlayer.getCurrentPosition() < duration) {
                ((PlayActivity) playActivity).updateProg(mPlayer.getCurrentPosition());
                //pb.setProgress(mPlayer.getCurrentPosition());
                Log.d("CURRENT", mPlayer.getCurrentPosition() + "");
            }
            return null;
        }
    }
}
