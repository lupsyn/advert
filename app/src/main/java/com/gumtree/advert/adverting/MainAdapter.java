package com.gumtree.advert.adverting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gumtree.advert.R;
import com.gumtree.advert.activity.MainActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mSetList;
    private MainActivity.Callback mCallback;

    public MainAdapter(Context context, List<String> setList) {
        mContext = context;
        mSetList = setList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String link = mSetList.get(position);
        if (link != null)
            Picasso.with(mContext).load(link).into(holder.mImageView, new Callback() {
                @Override
                public void onSuccess() {
                    if (mCallback != null) {
                        mCallback.onLoad();
                    }
                }

                @Override
                public void onError() {

                }
            });


    }

    @Override
    public int getItemCount() {
        return mSetList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;


        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.image);

        }
    }

    public void clear() {
        mSetList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<String> list, MainActivity.Callback callback) {
        this.mCallback = callback;
        mSetList.addAll(list);
        notifyDataSetChanged();
    }


}
