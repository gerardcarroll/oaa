package com.overstock.android.prototype.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;

import com.ogaclejapan.arclayout.ArcLayout;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.BrandActivity;
import com.overstock.android.prototype.activity.CommunityActivity;
import com.overstock.android.prototype.activity.FeedActivity;
import com.overstock.android.prototype.animatorutils.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment class to display Arc Menu.
 *
 * @author RayConnolly Created on 4/6/2016.
 */
public class ArcMenuFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.fab)
    ImageButton fab;

    @Bind(R.id.arcMenuBtn1)
    ImageButton imgBtnOne;

    @Bind(R.id.arcMenuBtn2)
    ImageButton imgBtnTwo;

    @Bind(R.id.arcMenuBtn3)
    ImageButton imgBtnThree;

    @Bind(R.id.menu_layout)
    View menuLayout;

    @Bind(R.id.arc_layout)
    ArcLayout arcLayout;

    public ArcMenuFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arc_menu, container, false);

        ButterKnife.bind(this, view);

        for (int i = 0, size = arcLayout.getChildCount(); i < size; i++) {
            arcLayout.getChildAt(i).setOnClickListener(this);
        }

        fab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.fab) {
            onFabClick(v);
            return;
        }

        if (v instanceof ImageButton) {

            final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                    R.transition.slide_in_vertical, R.transition.slide_out_vertical);

            if (v.equals(imgBtnOne)){
                startActivity(new Intent(getActivity(), FeedActivity.class), options.toBundle());
            }
            else if (v.equals(imgBtnTwo)){
                startActivity(new Intent(getActivity(), CommunityActivity.class), options.toBundle());
            }
            else if (v.equals(imgBtnThree)) {
                startActivity(new Intent(getActivity(), BrandActivity.class), options.toBundle());
            }
        }
    }

    private void onFabClick(View v) {
        if (v.isSelected()) {
            hideMenu();
            fab.setImageResource(R.drawable.ic_add);
        } else {
            showMenu();
            fab.setImageResource(R.drawable.ic_clear);
        }
        v.setSelected(!v.isSelected());
    }

    @SuppressWarnings("NewApi")
    private void showMenu() {
        menuLayout.setVisibility(View.VISIBLE);

        List<Animator> animList = new ArrayList<>();

        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new OvershootInterpolator());
        animSet.playTogether(animList);
        animSet.start();
    }

    @SuppressWarnings("NewApi")
    private void hideMenu() {

        List<Animator> animList = new ArrayList<>();

        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new AnticipateInterpolator());
        animSet.playTogether(animList);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                menuLayout.setVisibility(View.INVISIBLE);
            }
        });
        animSet.start();

    }

    private Animator createShowItemAnimator(View item) {

        float dx = fab.getX() - item.getX();
        float dy = fab.getY() - item.getY();

        item.setRotation(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 720f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );

        return anim;
    }

    private Animator createHideItemAnimator(final View item) {
        float dx = fab.getX() - item.getX();
        float dy = fab.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(720f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });

        return anim;
    }
}
