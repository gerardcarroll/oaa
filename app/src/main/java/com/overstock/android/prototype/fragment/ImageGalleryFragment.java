package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overstock.android.prototype.R;

/**
 * Created by rconnolly on 4/25/2016.
 */
public class ImageGalleryFragment extends Fragment {

    public ImageGalleryFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_gallery, container, false);
    }
}
