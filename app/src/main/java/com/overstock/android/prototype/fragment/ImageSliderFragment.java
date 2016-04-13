package com.overstock.android.prototype.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.overstock.android.prototype.R;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rconnolly on 4/13/2016.
 */
public class ImageSliderFragment extends Fragment {

    @Bind(R.id.slider)
    SliderLayout sliderLayout;

    public ImageSliderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_slider, container, false);

        ButterKnife.bind(this, view);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Bedding", R.drawable.bedding);
        file_maps.put("Fitness", R.drawable.cat_fitness);
        file_maps.put("Furniture", R.drawable.cat_furniture);
        file_maps.put("Men & Women", R.drawable.cat_men_and_women);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity().getApplicationContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra", name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);

        return view;
    }

    @Override
    public void onStop() {

        sliderLayout.stopAutoCycle();
        super.onStop();
    }
}
