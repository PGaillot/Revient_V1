package com.gayo.revient.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gayo.revient.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StuffListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StuffListFragment extends Fragment {

    public StuffListFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static StuffListFragment newInstance() {
        StuffListFragment fragment = new StuffListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stuff_list, container, false);
    }
}