package com.carbon.complete;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.carbon.complete.Fragments.Chat.ChatFragment;
import com.carbon.complete.Utils.Constants;


public class ChatActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    public static void startActivity(Context context,
                                     String receiverUid,
                                     String name ,
                                     String profilePicture) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.DatabaseTerms.RECEIVER_UID, receiverUid);
        intent.putExtra(Constants.DatabaseTerms.EMAIL, name);
        intent.putExtra(Constants.DatabaseTerms.PROFILE_PICTURE, profilePicture);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bindViews();
        init();
    }

    private void bindViews() {
        mToolbar = findViewById(R.id.toolbar);
    }

    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set toolbar title
        mToolbar.setTitle(getIntent().getExtras().getString(Constants.DatabaseTerms.EMAIL));

        // set the register screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,
                ChatFragment.newInstance(getIntent().getExtras().getString(Constants.DatabaseTerms.RECEIVER_UID),
                        getIntent().getExtras().getString(Constants.DatabaseTerms.EMAIL),
                        getIntent().getExtras().getString(Constants.DatabaseTerms.PROFILE_PICTURE)));
        fragmentTransaction.commit();
    }


}