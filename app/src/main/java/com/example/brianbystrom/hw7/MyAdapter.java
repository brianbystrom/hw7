package com.example.brianbystrom.hw7;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by brianbystrom on 3/9/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements PlayPodcastAsync.IData {
    private ArrayList<Data> mDataset;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout mLinearLayout;
        public TextView mPodcastTv, mPubDateTv;
        public ImageButton mPodcastIb;
        public ImageView mPodcastIv;
        public ViewHolder(View v) {
            super(v);
            mPodcastTv = (TextView) v.findViewById(R.id.podcast_tv);
            mPubDateTv = (TextView) v.findViewById(R.id.pubdate_tv);
            mPodcastIb = (ImageButton) v.findViewById(R.id.playcontrol_ib);
            mPodcastIv = (ImageView) v.findViewById(R.id.podcast_iv);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Data> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    private int convertMonthToNumber(String s){
        if(s.equals("Jan")){
            return 1;
        }
        else  if(s.equals("Feb")){
            return 2;
        }
        else  if(s.equals("Mar")){
            return 3;
        }
        else  if(s.equals("Apr")){
            return 4;
        }
        else  if(s.equals("May")){
            return 5;
        }
        else  if(s.equals("Jun")){
            return 6;
        }
        else  if(s.equals("July")){
            return 7;
        }
        else  if(s.equals("Aug")){
            return 8;
        }
        else  if(s.equals("Sep")){
            return 9;
        }
        else  if(s.equals("Jan")){
            return 10;
        }
        else  if(s.equals("Jan")){
            return 11;
        }
        else  if(s.equals("Jan")){
            return 12;
        }
        return 99;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //TextView tv = (TextView) holder.mLinearLayout.findViewById(R.id.podcast_tv);
        holder.mPodcastTv.setText(mDataset.get(position).getTitle());

        String day = mDataset.get(position).getTitle().substring(5,7);
        String month = mDataset.get(position).getTitle().substring(5,7);
        String year;
        holder.mPubDateTv.setText(mDataset.get(position).getPublished_date());


        Log.d("IMAGE URL", mDataset.get(position).getUrlToImage());

        holder.mPodcastIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PLAY", "PLAY" + mDataset.get(position).getUrlToMp3());
                new PlayPodcastAsync(MyAdapter.this).execute(mDataset.get(position).getUrlToMp3());


            }
        });//

        if (!mDataset.get(position).getUrlToImage().toString().equals("")) {
            Picasso.with(mContext).load(mDataset.get(position).getUrlToImage()).into(holder.mPodcastIv);

        }



    }

    public void playPodcast(MediaPlayer mPlayer) {
        mPlayer.start();
        int duration = 0;
        int current = 0;
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

