package com.carbon.complete.Utils;

/**
 * Created by archlinux on 5/1/18.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.carbon.complete.Fragments.ProfileSetup.WelcomePageOne;
import com.carbon.complete.Fragments.ProfileSetup.WelcomePageTwo;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        return WelcomePagerEnum.values().length > 0 ? WelcomePagerEnum.values().length : 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return WelcomePageOne.newInstance();
            case 1:
                return WelcomePageTwo.newInstance();
            case 2:
                return WelcomePageTwo.newInstance();


        }
        return null;
    }

}
