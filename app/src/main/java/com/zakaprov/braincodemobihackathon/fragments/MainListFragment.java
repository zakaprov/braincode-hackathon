package com.zakaprov.braincodemobihackathon.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zakaprov.braincodemobihackathon.MainActivity;
import com.zakaprov.braincodemobihackathon.R;
import com.zakaprov.braincodemobihackathon.adapters.MainListAdapter;
import com.zakaprov.braincodemobihackathon.callbacks.InterestContainerCallback;
import com.zakaprov.braincodemobihackathon.model.Interest;
import com.zakaprov.braincodemobihackathon.model.InterestContainer;

public class MainListFragment extends AbstractFragment
{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Interest[] dataSource;

    private static final int NUMBER_OF_COLUMNS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.main_list_fragment, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.mainRecyclerView);

        InterestContainer interestContainer = new InterestContainer();
        interestContainer.getInterestsAsync(new MainListCallback());

        mLayoutManager = new GridLayoutManager(getActivity(), NUMBER_OF_COLUMNS);
        mRecyclerView.setLayoutManager(mLayoutManager);

        return layout;
    }

    private class MainListCallback implements InterestContainerCallback
    {
        @Override
        public void onDownloadComplete(Interest[] result)
        {
            dataSource = result;

            mAdapter = new MainListAdapter(dataSource, (MainActivity)getActivity());
            mRecyclerView.setAdapter(mAdapter);

        }
    }
}
