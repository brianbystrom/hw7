package com.example.brianbystrom.hw7;

import android.content.Context;
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

import java.util.ArrayList;

/**
 * Created by brianbystrom on 3/9/17.
 */

public class MyAdapterGrid extends RecyclerView.Adapter<MyAdapterGrid.ViewHolder> {
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
            mPodcastIv = (ImageView) v.findViewById(R.id.podcast_iv);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapterGrid(ArrayList<Data> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterGrid.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_layout_grid, parent, false);
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
        Log.d("IMAGE URL", mDataset.get(position).getUrlToImage());

        /*holder.mPodcastIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PLAY", "PLAY" + mDataset.get(position).getUrlToMp3());
                new PlayPodcastAsync().execute(mDataset.get(position).getUrlToMp3());


            }
        });*/

        if (!mDataset.get(position).getUrlToImage().toString().equals("")) {
            Picasso.with(mContext).load(mDataset.get(position).getUrlToImage()).into(holder.mPodcastIv);

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


