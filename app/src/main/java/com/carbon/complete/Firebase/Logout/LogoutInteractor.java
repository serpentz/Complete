package com.carbon.complete.Firebase.Logout;

import android.support.annotation.NonNull;

import com.carbon.complete.Utils.Constants;
import com.carbon.complete.Firebase.Login.LoginInteractor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class LogoutInteractor implements LogoutContract.Interactor {
    String TAG = LoginInteractor.class.getCanonicalName();
    private LogoutContract.OnLogoutListener mOnLogoutListener;

    public LogoutInteractor(LogoutContract.OnLogoutListener onLogoutListener) {
        mOnLogoutListener = onLogoutListener;
    }

    @Override
    public void performFirebaseLogout() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {


         // updateFirebaseToken(FirebaseAuth.getInstance().getCurrentUser().getUid());
          FirebaseAuth.getInstance().signOut();


            mOnLogoutListener.onSuccess("Successfully logged out!");
        } else {
            mOnLogoutListener.onFailure("No user logged in yet!");
        }
    }

    private void updateFirebaseToken(String uid) {


        FirebaseDatabase.getInstance().getReference().child(Constants.DatabaseTerms.USERS)
                .child(uid).child(Constants.DatabaseTerms.DATA)
                .child(Constants.DatabaseTerms.TOKEN).setValue("logout").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }}