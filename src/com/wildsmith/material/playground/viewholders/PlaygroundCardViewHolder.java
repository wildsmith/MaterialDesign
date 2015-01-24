package com.wildsmith.material.playground.viewholders;

import android.app.Activity;
import android.view.View;

import com.wildsmith.material.recycler.RecyclerViewHolder;

public abstract class PlaygroundCardViewHolder extends RecyclerViewHolder {

    private View container;

    public PlaygroundCardViewHolder(View view) {
        super(view);

        container = view;
    }

    protected Activity mActivity;

    public abstract View getTitleTextView(View view);

    public abstract View getInfoTextView(View view);

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public View getContainer() {
        return container;
    }
}