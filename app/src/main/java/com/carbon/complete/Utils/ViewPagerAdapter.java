package com.carbon.complete.Utils;

/**
 * Created by archlinux on 5/1/18.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carbon.complete.Fragments.ProfileSetup.NameFragment;
import com.carbon.complete.Fragments.Test;

/**
 * Created by ramraj on 10/3/17.
 */

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
        switch(position){
            case 0 :  return NameFragment.newInstance();
            case 1:   return NameFragment.newInstance();

                case 2: return Test.newInstance("4");

        }
        return null;
    }

}
