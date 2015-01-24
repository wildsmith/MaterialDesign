package com.wildsmith.material.playground.viewholders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;

import com.wildsmith.material.R;

public class PlaygroundActionCardViewHolder extends PlaygroundCardViewHolder {

    private CardView cardView;

    public PlaygroundActionCardViewHolder(View view) {
        super(view);

        cardView = (CardView) view.findViewById(R.id.card_view);
    }

    @Override
    public void populateView(Context context, Object recyclerObject, int position) {
        cardView.setTransitionName("card:view:" + position);
    }

    @Override
    public View getTitleTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.action_title_text);
    }

    @Override
    public View getInfoTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.action_edit_text);
    }
}