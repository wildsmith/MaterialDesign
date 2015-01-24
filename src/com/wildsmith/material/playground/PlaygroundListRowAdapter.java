package com.wildsmith.material.playground;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.list.ListRowAdapter;
import com.wildsmith.material.playground.rows.PlaygroundActionBarCardRow;
import com.wildsmith.material.playground.rows.PlaygroundActionCardRow;
import com.wildsmith.material.playground.rows.PlaygroundElevationCardRow;
import com.wildsmith.material.playground.rows.PlaygroundNavigationCardRow;

public class PlaygroundListRowAdapter extends ListRowAdapter {

    private static final String TAG = PlaygroundListRowAdapter.class.getSimpleName();

    public PlaygroundListRowAdapter(Context pContext, int pViewResourceId) {
        super(pContext, pViewResourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListRow mRow = getRow(position);
        if (mRow == null) {
            return null;
        }

        try {
            View view = mRow.getView(mInflater, convertView, mContext, position);
            return view;
        } catch (Exception e) {
            Log.e(TAG, "Could not create the given row for position: " + position + "." + e.getMessage());
        }

        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (mRows == null || mRows.isEmpty() || mRows.size() < position) {
            return -1;
        }

        ListRow row = mRows.get(position);
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
}