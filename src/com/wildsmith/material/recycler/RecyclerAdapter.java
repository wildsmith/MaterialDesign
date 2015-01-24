package com.wildsmith.material.recycler;

import java.util.List;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

public abstract class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected List<? extends Object> recyclerObjects;

    protected Activity activity;

    public RecyclerAdapter(List<? extends Object> recyclerObjects, Activity activity) {
        this.recyclerObjects = recyclerObjects;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Object recyclerObject = getRecyclerObject(position);
        if (recyclerObject == null) {
            return;
        }

        holder.populateView(activity, recyclerObject, position);
    }

    @Override
    public int getItemCount() {
        if (recyclerObjects == null) {
            return 0;
        }

        return recyclerObjects.size();
    }

    protected Object getRecyclerObject(int position) {
        if (recyclerObjects == null) {
            return null;
        }

        if (recyclerObjects.size() <= position || position < 0) {
            return null;
        }

        return recyclerObjects.get(position);
    }

    public List<? extends Object> getRecyclerObjects() {
        return recyclerObjects;
    }

    public void setRecyclerObjects(List<? extends Object> recyclerObjects) {
        this.recyclerObjects = recyclerObjects;
    }
}