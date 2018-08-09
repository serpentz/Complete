package com.carbon.complete;



import android.content.Context;
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
import top.defaults.view.TextButton;

public class ProfileSetupActivity extends CoreActivity implements View.OnClickListener {

    private CircleIndicator circleIndicator;
    private TextButton sign_in, sign_up;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private int[] color;


    public static void startActivity(Context context) {

        Intent intent = new Intent(context, ProfileSetupActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        color = new int[]{
                getContext().getResources().getColor(R.color.user_profile_background_white),
                getContext().getResources().getColor(R.color.user_profile_background_white),

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
        sign_in = findViewById(R.id.welcome_screen_sign_in);
        sign_up = findViewById(R.id.welcome_screen_sign_up);
        viewPager = findViewById(R.id.intro_silder_pager);
    }

    @Override
    protected void setListeners() {

        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.welcome_screen_sign_in:
                LoginActivity.startActivity(this);
                break;
            case R.id.welcome_screen_sign_up:
                RegisterActivity.startActivity(this);
                break;



        }
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
