package com.carbon.complete;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.carbon.complete.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditCardFragment extends Fragment {


    public CreditCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credit_card, container, false);
    }

    public static CreditCardFragment newInstance() {

        return new CreditCardFragment();
    }
}
