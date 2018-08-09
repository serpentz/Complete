package com.carbon.complete.Fragments.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import com.carbon.complete.Fragments.Home.Graph.LineCardOne;
import com.carbon.complete.R;
import com.carbon.complete.Utils.Constants;
import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.view.LineChartView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private final String[] mLabels = {"Jan", "Fev", "Mar", "Apr", "Jun", "May", "Jul", "Aug", "Sep"};

    private final float[][] mValues = {{3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 7f, 8.3f, 7.0f},
            {4.5f, 2.5f, 2.5f, 9f, 4.5f, 9.5f, 5f, 8.3f, 1.8f}};


    public static HomeFragment newInstance(String STR) {
        HomeFragment fragment = new HomeFragment();

        Bundle b = new Bundle();

        b.putString(Constants.DatabaseTerms.EMAIL, STR);
        fragment.setArguments(b);

        return fragment;
    }


    @Override
    public void onPause() {
        super.onPause();
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

    private void init(final View v) {


        LineChartView chart = v.findViewById(R.id.chart);

        LineSet dataset = new LineSet(mLabels, mValues[0]);
        dataset.setColor(Color.parseColor("#00ca9d"))
                .setThickness(10)
                .beginAt(0);
        dataset.setSmooth(true);
        chart.addData(dataset);




        Runnable chartAction = new Runnable() {
            @Override
            public void run() {


            }
        };

        chart.setAxisBorderValues(0, 20)
                .setYLabels(AxisRenderer.LabelPosition.NONE)
                .show(new Animation().setInterpolator(new BounceInterpolator())
                        .fromAlpha(0)
                        .withEndAction(chartAction));






    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }
    private void resetanim(View v){
        v.animate().setStartDelay(0).setDuration(0).translationY(-200).scaleX(1).scaleY(1).start();
    }

}
