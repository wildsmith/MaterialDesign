package com.wildsmith.material.playground.viewholders;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.playground.detail.PlaygroundDetailActivity;
import com.wildsmith.material.utils.ResourceHelper;

public class PlaygroundNavigationCardViewHolder extends PlaygroundCardViewHolder {

    private CardView cardView;

    private TextView titleText;

    private TextView infoText;

    public PlaygroundNavigationCardViewHolder(View view) {
        super(view);

        cardView = (CardView) view.findViewById(R.id.card_view);
        titleText = (TextView) view.findViewById(R.id.navigation_title_text);
        infoText = (TextView) view.findViewById(R.id.navigation_info_text);
    }

    @Override
    public void populateView(Context context, Object recyclerObject, int position) {
        cardView.setTransitionName("card:view:" + position);
        titleText.setTransitionName("nav:title:" + position);
        infoText.setTransitionName("nav:info:" + position);

        setupCardViewListener();
    }

    @Override
    public View getTitleTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.navigation_title_text);
    }

    @Override
    public View getInfoTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.navigation_info_text);
    }

    private void setupCardViewListener() {
        cardView.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, PlaygroundDetailActivity.class);
                intent.putExtra("ActionBarShowing", mActivity.getActionBar().isShowing());

                final View titleText = getTitleTextView(view);
                final String titleViewName = ResourceHelper.getResString(R.string.detail_title_view_name);

                final View infoText = getInfoTextView(view);
                final String infoViewName = ResourceHelper.getResString(R.string.detail_info_view_name);

                ActivityOptions activityOptions =
                        ActivityOptions.makeSceneTransitionAnimation(mActivity, new Pair<View, String>(titleText, titleViewName),
                                new Pair<View, String>(infoText, infoViewName));

                mActivity.startActivity(intent, activityOptions.toBundle());
            }
        });
    }
}