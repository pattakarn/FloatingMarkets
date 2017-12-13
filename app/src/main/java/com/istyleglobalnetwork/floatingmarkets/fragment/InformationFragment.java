package com.istyleglobalnetwork.floatingmarkets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.istyleglobalnetwork.floatingmarkets.R;

public class InformationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private int image;

    public InformationFragment() {
        // Required empty public constructor
    }

    public static InformationFragment newInstance(int image) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putInt("image", image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getInt("image");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_information, container, false);
        ImageView iv = (ImageView) rootView.findViewById(R.id.iv);
        iv.setImageResource(image);

        return rootView;
    }

}
