package com.carbon.complete;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.carbon.complete.Fragments.HomeFragment;
import com.carbon.complete.Fragments.UserProfileFragment;
import com.carbon.complete.Fragments.Users.UsersFragment;
import com.carbon.complete.Utils.Constants;
import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;
import com.google.firebase.auth.FirebaseAuth;

import custom.StiffViewPager;

public class MainActivity extends AppCompatActivity implements UserProfileFragment.OnFragmentInteractionListener {

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





        bottomNav.addItemNav(new ItemNav(this, R.drawable.ic_groups).addColorAtive(R.color.primaryGreen).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.ic_credit_card).addColorAtive(R.color.primaryGreen).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.ic_chat).addColorAtive(R.color.primaryGreen).addColorInative(R.color.background_color_light));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.ic_user, R.drawable.ic_user).isProfileItem().addColorInative(R.color.background_color_light).addColorAtive(R.color.primaryGreen));

        bottomNav.build();

        setProfilePicture();

        SetListeners();
        bottomNav.selectTab(2);


    }

    private void setProfilePicture() {


        if (UserProfileFragment.checkPermissionForReadExtertalStorage(this))
            bottomNav.updateImageProfile(Constants.FULL_PATH_TO_PICTURES + "/profile_picture.jpg");

        Log.e(TAG, Constants.FULL_PATH_TO_PICTURES + "/profile_picture.jpg");
     //   Log.e(TAG, Constants.PATH_TO_INTERNAL_MEMORY+Constants.FULL_PATH_TO_PICTURES + "/profile_picture.jpg");




    }


    private void SetListeners() {


        BottomNav.OnTabSelectedListener listener = new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

                Fragment fragment = null;
                switch (position) {

                    case 0:

                        fragment= HomeFragment.newInstance(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                        break;
                    case 1:

                        fragment= CreditCardFragment.newInstance();
                        break;
                    case 2:

                        fragment= UsersFragment.newInstance(1);
                        break;
                    case 3:

                        fragment= UserProfileFragment.newInstance("4");
                        break;

                }



                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainActivity_container, fragment);
                transaction.commit();





            }

            @Override
            public void onTabLongSelected(int position) {




            }
        };
        bottomNav.setTabSelectedListener(listener);


    }


    @Override
    public void onFragmentInteraction(Uri uri) {


    }

}


