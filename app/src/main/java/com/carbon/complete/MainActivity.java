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
import android.util.Log;
import android.view.View;

import com.carbon.complete.Fragments.HomeFragment;
import com.carbon.complete.Fragments.Test;
import com.carbon.complete.Fragments.Users.UsersFragment;
import com.carbon.complete.Utils.Constants;
import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;
import com.google.firebase.auth.FirebaseAuth;

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
        view_pager.setCurrentItem(2);
        view_pager.setEnabled(false);


        bottomNav.selectTab(2);
        view_pager.setAdapter(new MyScreenAdapter(getSupportFragmentManager()));

        bottomNav.addItemNav(new ItemNav(this, R.drawable.ic_nav_home).addColorAtive(R.color.selected_color).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.ic_nav_nearby).addColorAtive(R.color.primaryGreen).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.speech_bubble).addColorAtive(R.color.blue_selected_color).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.user, R.drawable.user).isProfileItem().addProfileColorAtive(android.R.color.holo_red_dark).addProfileColorInative(android.R.color.black));

        bottomNav.build();

        setProfilePicture();

        SetListeners();


    }

    private void setProfilePicture() {


        if (Test.checkPermissionForReadExtertalStorage(this))
            bottomNav.updateImageProfile(Constants.FULL_PATH_TO_PICTURES + "/profile_picture.jpg");

        Log.e(TAG, Constants.FULL_PATH_TO_PICTURES + "/profile_picture.jpg");
        view_pager.clearAnimation();
        view_pager.setAnimation(null);
        view_pager.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });


    }


    private void SetListeners() {

        BottomNav.OnTabSelectedListener listener = new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

                if (position <= 2) {
                    view_pager.setCurrentItem(2);
                } else {
                    view_pager.setCurrentItem(position);
                }

            }

            @Override
            public void onTabLongSelected(int position) {

                view_pager.setCurrentItem(position);

            }
        };
        bottomNav.setTabSelectedListener(listener);
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

    }


    @Override
    public void onFragmentInteraction(Uri uri) {


    }

    private class MyScreenAdapter extends FragmentStatePagerAdapter {


        public MyScreenAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:

                    return HomeFragment.newInstance(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                case 1:

                    return Test.newInstance("2");
                case 2:

                    return UsersFragment.newInstance(1);
                case 3:

                    return Test.newInstance("4");

            }

            return null;
        }


    }
}


