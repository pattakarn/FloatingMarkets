package com.istyleglobalnetwork.floatingmarkets.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.istyleglobalnetwork.floatingmarkets.R;

public class ImageFragment extends Fragment {

    ImageView iv;
    Uri image;

    LayoutInflater inflater;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(Uri image1) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
//        args.putInt("image", image1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            image = getArguments().getInt("image");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        this.inflater = inflater;
        initInstances(rootView);

//        iv.setImageResource(image);
        Glide.with(inflater.getContext())
                .load(image.toString())
                .placeholder(R.mipmap.ic_floating_market)
                .into(iv);

        return rootView;
    }

    private void initInstances(View rootView) {
        // init instance with rootView.findViewById here

        iv = (ImageView) rootView.findViewById(R.id.iv);
//        SignInButton signInButton = (SignInButton) rootView.findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        signInButton.setOnClickListener(this);
    }


}
