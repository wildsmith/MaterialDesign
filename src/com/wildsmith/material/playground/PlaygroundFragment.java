package com.wildsmith.material.playground;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.core.CoreMaterialFragment;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.playground.rows.PlaygroundActionBarCardRow;
import com.wildsmith.material.playground.rows.PlaygroundActionCardRow;
import com.wildsmith.material.playground.rows.PlaygroundCardListRow;
import com.wildsmith.material.playground.rows.PlaygroundElevationCardRow;
import com.wildsmith.material.playground.rows.PlaygroundNavigationCardRow;
import com.wildsmith.material.utils.ResourceHelper;
import com.wildsmith.material.views.FloatingActionButton;

public class PlaygroundFragment extends CoreMaterialFragment implements OnScrollListener {

    public static final String TAG = PlaygroundFragment.class.getSimpleName();

    private ListView mListView;

    private RecyclerView mRecyclerView;

    private PlaygroundListRowAdapter mListAdapter;

    private PlaygroundRecyclerRowAdapter mRecyclerAdapter;

    private FloatingActionButton fabButton;

    private int mLastFirstVisibleItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playground_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view == null) {
            return;
        }

        // setupListView(view);
        setupRecyclerView(view);
        setupFabView(view);
    }

    private void setupFabView(View view) {
        fabButton = (FloatingActionButton) view.findViewById(R.id.fab_button);
    }

    private void setupListView(View view) {
        if (mListView == null) {
            mListView = (ListView) view.findViewById(R.id.playground_list);
            mListView.setOnScrollListener(this);
        }

        if (mListAdapter == null) {
            mListAdapter = new PlaygroundListRowAdapter(getActivity(), R.layout.playground_action_card_row);
        }

        if (mListAdapter.getCount() == 0) {
            mListAdapter.setRows(setupPlaygroundData());
        }

        if (mListView.getAdapter() == null) {
            mListView.setAdapter(mListAdapter);
        }
    }

    private void setupRecyclerView(View view) {
        if (mRecyclerAdapter == null) {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.playground_recycler);
            mRecyclerView.setHasFixedSize(true);

            mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    final int actionBarHeight = ResourceHelper.getActionBarHeight();
                    if (dy >= ResourceHelper.getActionBarHeight()) {
                        getActivity().getActionBar().hide();
                        ((CoreMaterialActivity) getActivity()).setupDrawerListRows(false);
                        fabButton.hide();
                    }

                    if (dy < 0 && (dy * -1) >= actionBarHeight) {
                        getActivity().getActionBar().show();
                        ((CoreMaterialActivity) getActivity()).setupDrawerListRows(true);
                        fabButton.show();
                    }
                }
            });
        }

        if (mRecyclerView.getLayoutManager() == null) {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        if (mRecyclerView.getAdapter() == null) {
            mRecyclerAdapter = new PlaygroundRecyclerRowAdapter(setupPlaygroundData(), getActivity());
            mRecyclerView.setAdapter(mRecyclerAdapter);
        }
    }

    private List<ListRow> setupPlaygroundData() {
        List<ListRow> rows = new ArrayList<ListRow>(20);
        int count = 0;
        for (int i = 0; i < 20; i++) {
            if (i % 3 == 0) {
                count = 0;
            }

            PlaygroundCardListRow listRow = null;
            if (i == 0) {
                listRow = new PlaygroundActionBarCardRow();
            } else if (count == 0) {
                listRow = new PlaygroundActionCardRow();
            } else if (count == 1) {
                listRow = new PlaygroundNavigationCardRow();
            } else {
                listRow = new PlaygroundElevationCardRow();
            }

            listRow.setActivity(getActivity());
            rows.add(listRow);

            count++;
        }
        return rows;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        final int currentFirstVisibleItem = mListView.getFirstVisiblePosition();

        if (currentFirstVisibleItem > mLastFirstVisibleItem) {
            getActivity().getActionBar().hide();
            ((CoreMaterialActivity) getActivity()).setupDrawerListRows(false);
            fabButton.hide();
        } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
            getActivity().getActionBar().show();
            ((CoreMaterialActivity) getActivity()).setupDrawerListRows(true);
            fabButton.show();
        }

        mLastFirstVisibleItem = currentFirstVisibleItem;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}