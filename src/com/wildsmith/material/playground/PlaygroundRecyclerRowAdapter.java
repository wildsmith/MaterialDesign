package com.wildsmith.material.playground;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wildsmith.material.R;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.playground.rows.PlaygroundActionBarCardRow;
import com.wildsmith.material.playground.rows.PlaygroundActionCardRow;
import com.wildsmith.material.playground.rows.PlaygroundElevationCardRow;
import com.wildsmith.material.playground.rows.PlaygroundNavigationCardRow;
import com.wildsmith.material.playground.viewholders.PlaygroundActionBarCardViewHolder;
import com.wildsmith.material.playground.viewholders.PlaygroundActionCardViewHolder;
import com.wildsmith.material.playground.viewholders.PlaygroundCardViewHolder;
import com.wildsmith.material.playground.viewholders.PlaygroundElevationCardViewHolder;
import com.wildsmith.material.playground.viewholders.PlaygroundNavigationCardViewHolder;
import com.wildsmith.material.recycler.RecyclerAdapter;
import com.wildsmith.material.recycler.RecyclerViewHolder;

public class PlaygroundRecyclerRowAdapter extends RecyclerAdapter {

    private int mPosition;

    public PlaygroundRecyclerRowAdapter(List<ListRow> recyclerObjects, Activity activity) {
        super(recyclerObjects, activity);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder = null;
        View view = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playground_action_bar_card_row, parent, false);
                viewHolder = new PlaygroundActionBarCardViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playground_action_card_row, parent, false);
                viewHolder = new PlaygroundActionCardViewHolder(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playground_navigation_card_row, parent, false);
                viewHolder = new PlaygroundNavigationCardViewHolder(view);
                break;
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playground_elevation_card_row, parent, false);
                viewHolder = new PlaygroundElevationCardViewHolder(view);
                break;
            default:
                break;
        }

        if (viewHolder != null && viewHolder instanceof PlaygroundCardViewHolder) {
            ((PlaygroundCardViewHolder) viewHolder).setActivity(activity);
        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        Object row = getRecyclerObject(position);
        if (row instanceof PlaygroundActionBarCardRow) {
            return 0;
        } else if (row instanceof PlaygroundActionCardRow) {
            return 1;
        } else if (row instanceof PlaygroundNavigationCardRow) {
            return 2;
        } else if (row instanceof PlaygroundElevationCardRow) {
            return 3;
        }

        return -1;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        setAnimation(holder.getContainer(), position);
    }

    private void setAnimation(View view, int position) {
        if (position > mPosition) {
            Animation animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left);
            view.startAnimation(animation);
        }

        mPosition = position;
    }
}