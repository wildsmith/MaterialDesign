package com.wildsmith.material.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public abstract class RecyclerViewHolder extends ViewHolder {

    private View container;

    public RecyclerViewHolder(View view) {
        super(view);

        container = view;
    }

    public abstract void populateView(Context context, Object recyclerObject, int position);

    public View getContainer() {
        return container;
    }
}