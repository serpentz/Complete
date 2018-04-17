package com.carbon.complete.Firebase.Login;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.carbon.complete.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by archlinux on 3/7/18.
 */

public class LoginInteractor implements LoginInterface.Interactor {

    private LoginInterface.OnLoginListener mOnLoginListener;

    public LoginInteractor(LoginInterface.OnLoginListener onUserDatabaseListener) {
        this.mOnLoginListener = onUserDatabaseListener;
    }

    @Override
    public void LoginUser(Activity context, String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                            final String token = FirebaseInstanceId.getInstance().getToken();
                            final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                mOnLoginListener.onSuccess(task.getResult().toString());
                                updateFirebaseToken(uid, token);


                            } else {
                                mOnLoginListener.onFailure(task.getException().getMessage());
                            }

                    }
                });
    }

    private void updateFirebaseToken(String uid, String token) {


        FirebaseDatabase.getInstance().getReference()
                .child(Constants.DatabaseTerms.USERS)
                .child(uid)
                .child(Constants.DatabaseTerms.DATA)
                .child(Constants.DatabaseTerms.TOKEN)
                .setValue(token);



    }

}