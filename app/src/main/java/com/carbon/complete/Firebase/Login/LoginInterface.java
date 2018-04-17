package com.carbon.complete.Firebase.Login;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by archlinux on 3/7/18.
 */

public interface LoginInterface {

    interface View {
        void onLoginSucces(String message);

        void onLoginFailure(String message);
    }

    interface Presenter {
        void LoginUser(Activity context, String email, String password);
    }

    interface Interactor {
        void LoginUser(Activity context, String email, String password);
    }

    interface OnLoginListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

}
