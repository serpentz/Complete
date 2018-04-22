package com.carbon.complete;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.carbon.complete.Fragments.Test;
import com.carbon.complete.Fragments.Users.UsersFragment;
import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;

import custom.StiffViewPager;

public class MainActivity extends AppCompatActivity implements Test.OnFragmentInteractionListener {

    public static final int GALLERY_REQUEST = 2000;
    public static final int IMAGE_REQUEST = 2500;
    public static BottomNav bottomNav;
    private final int NUM_PAGES = 4;
    private StiffViewPager view_pager;
    private String TAG = MainActivity.class.getSimpleName();

    public static void startActivity(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    private void init() {

        bottomNav = findViewById(R.id.bottomNav);
        view_pager = findViewById(R.id.view_pager);
        view_pager.setCurrentItem(0);
        view_pager.setEnabled(false);

        bottomNav.selectTab(0);
        view_pager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager()));


        bottomNav.addItemNav(new ItemNav(this, R.drawable.speech_bubble).addColorAtive(R.color.selected_color).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.ic_nav_nearby).addColorAtive(R.color.primaryGreen).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.speech_bubble).addColorAtive(R.color.blue_selected_color).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.user, R.drawable.user).isProfileItem().addProfileColorAtive(android.R.color.holo_red_dark).addProfileColorInative(android.R.color.black));

        bottomNav.build();

        SetListeners();


    }



    private void SetListeners() {

        BottomNav.OnTabSelectedListener listener = new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

                view_pager.setCurrentItem(position);
            }

            @Override
            public void onTabLongSelected(int position) {

                view_pager.setCurrentItem(position);
            }
        };
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNav.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNav.setTabSelectedListener(listener);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {


    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:

                    return Test.newInstance("1");
                case 1:

                    return UsersFragment.newInstance(1);
                case 2:

                    return Test.newInstance("3");
                case 3:

                    return Test.newInstance("4");

            }
            return null;

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}


