package com.wildsmith.material.playground.viewholders;

import android.content.Context;
import android.view.View;

import com.wildsmith.material.R;

public class PlaygroundActionBarCardViewHolder extends PlaygroundCardViewHolder {

    private View actionBarView;

    public PlaygroundActionBarCardViewHolder(View view) {
        super(view);

        actionBarView = view.findViewById(R.id.action_bar_view);
    }

    @Override
    public void populateView(Context context, Object recyclerObject, int position) {
        actionBarView.setTransitionName("card:view:" + position);
    }

    @Override
    public View getTitleTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.elevation_title_text);
    }

    @Override
    public View getInfoTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.elevation_info_text);
    }
}