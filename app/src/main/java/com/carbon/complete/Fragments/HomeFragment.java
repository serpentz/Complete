package com.carbon.complete.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.carbon.complete.R;


public class HomeFragment extends Fragment{

    private View bgheadr, profpic, txtusername, ic_menu2,ic_menu1,lst1,lst2,lst3,lst4;
    private static int counter = 0;

    public static HomeFragment newInstance(String STR) {
        HomeFragment fragment = new HomeFragment();
counter++;
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;

    }

    private void init(View v) {


        profpic=v.findViewById(R.id.fragmentdashboardPic);
        txtusername=v.findViewById(R.id.fragmentDashboardEmail);
        bgheadr=v.findViewById(R.id.fragmentdashboardImageView1);
        lst1=v.findViewById(R.id.fragmentdashboardLinearLayout1);
        lst2=v.findViewById(R.id.fragmentdashboardLinearLayout2);
        lst3=v.findViewById(R.id.fragmentdashboardLinearLayout3);
        lst4=v.findViewById(R.id.fragmentdashboardLinearLayout4);

        bgheadr.animate().setStartDelay(0).scaleX(2).scaleY(2).setDuration(0).start();


    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if(counter==1) {
            resetanim(lst1);
            resetanim(lst2);
            resetanim(lst3);
            resetanim(lst4);
            txtusername.animate().setStartDelay(300).setDuration(2000).alpha(1).start();
            profpic.animate().setStartDelay(0).setDuration(0).scaleX(0).scaleY(0).start();
            profpic.animate().setStartDelay(500).setDuration(2000).setInterpolator(new OvershootInterpolator()).scaleX(1).scaleY(1).start();

            bgheadr.animate().setStartDelay(200).setDuration(1500).scaleX(1).scaleY(1).start();


        }
    }
    private void resetanim(View v){
        v.animate().setStartDelay(0).setDuration(0).translationY(-200).scaleX(1).scaleY(1).start();
    }

}
