package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String page_number;
    private String page_all;

    public InformationFragment() {
        // Required empty public constructor
    }

    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page_number = getArguments().getString(ARG_PARAM1);
            page_all = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_information, container, false);
        ImageView iv = (ImageView) rootView.findViewById(R.id.iv);
        TextView tv = (TextView) rootView.findViewById(R.id.tv);
        tv.setText(page_number + " / " + page_all);
        if (page_number.equals("1")) {
            iv.setImageResource(R.drawable.intro1);
        } else if (page_number.equals("2")) {
            iv.setImageResource(R.drawable.intro2);
        } else if (page_number.equals("3")) {
            iv.setImageResource(R.drawable.intro3);
        }

        return rootView;
    }

}
