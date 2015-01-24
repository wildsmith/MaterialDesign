package com.wildsmith.material.playground.viewholders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;

import com.wildsmith.material.R;

public class PlaygroundElevationCardViewHolder extends PlaygroundCardViewHolder {

    private CardView cardView;

    public PlaygroundElevationCardViewHolder(View view) {
        super(view);

        cardView = (CardView) view.findViewById(R.id.card_view);
    }

    @Override
    public void populateView(Context context, Object recyclerObject, int position) {
        cardView.setTransitionName("card:view:" + position);

        setupCardViewListener();
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

    @SuppressLint("ClickableViewAccessibility")
    private void setupCardViewListener() {
        cardView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                /* Raise view on ACTION_DOWN and lower it on ACTION_UP. */
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        view.setTranslationZ(20);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setTranslationZ(0);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }
}