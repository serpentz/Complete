package com.carbon.complete.Fragments.ProfileSetup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carbon.complete.R;

/**
 * Created by archlinux on 5/1/18.
 */

public class WelcomePageThree extends Fragment {

    public static WelcomePageThree newInstance(){
        WelcomePageThree frag = new WelcomePageThree();
        return frag;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_welcome_screen_three, container, false);
        init(view) ;
        return view;

    }

    private void init(View v) {





    }
}
