package com.overstock.android.prototype.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rconnolly on 5/5/2016.
 */
public class ProductDetailMoreInfoAdapter extends PagerAdapter {

    private List<View> views = new ArrayList<>();

    public ProductDetailMoreInfoAdapter(List<View> pagerViews) {
        this.views = pagerViews;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle = String.valueOf(views.get(position).getTag());
        return pageTitle;
    }

    @Override
    public int getItemPosition (Object object)
    {
        int index = views.indexOf(object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View v = views.get(position);
        container.addView(v, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.notifyDataSetChanged();
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

}
