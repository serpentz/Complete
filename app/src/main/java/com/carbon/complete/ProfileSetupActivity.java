package com.carbon.complete;

import android.animation.ArgbEvaluator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.carbon.complete.Utils.ViewPagerAdapter;


import me.relex.circleindicator.CircleIndicator;

public class ProfileSetupActivity extends CoreActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private CircleIndicator circleIndicator;
    private View next;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;




    private int[] color;


    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);





        color = new int[]{
                getContext().getResources().getColor(R.color.background_color_light),
                getContext().getResources().getColor(R.color.blue_selected_color),
                getContext().getResources().getColor(R.color.colorPrimaryDark)
        };

        //set ViewPager adapter
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //set viewPager Indicator
        circleIndicator.setViewPager(viewPager);

    }

    @Override
    protected void bindViews() {
        circleIndicator = findViewById(R.id.circulator_indicator);

        next = findViewById(R.id.next);

        viewPager = findViewById(R.id.intro_silder_pager);
    }

    @Override
    protected void setListeners() {

        next.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }



    private void startHomeActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /*viewPager Page change listener starting*/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position < (pagerAdapter.getCount() - 1) && position < (color.length - 1)) {
            viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, color[position], color[position + 1]));
        } else {
            viewPager.setBackgroundColor(color[color.length - 1]);
        }
    }

    @Override
    public void onPageSelected(int position) {

        switch(position){
            case 0: viewPager.setEnabled(false);
                break;
            case 1: viewPager.setEnabled(false);
                break;
            case 2: viewPager.setEnabled(false);
                break;


        }

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.next:
                if (viewPager.getCurrentItem() < 2) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    
                }
                else
                    startHomeActivity();
                break;

        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /*viewPager Page change listener ending*/

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
