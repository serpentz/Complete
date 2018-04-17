package com.carbon.complete.Firebase.Login;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by archlinux on 3/7/18.
 */

public class LoginPresenter implements LoginInterface.Presenter, LoginInterface.OnLoginListener {
    private LoginInterface.View mRegisterView;
    private LoginInteractor mLoginView;

    public LoginPresenter(LoginInterface.View registerView) {
        this.mRegisterView = registerView;
        mLoginView = new LoginInteractor(this);
    }

    @Override
    public void onSuccess(String message) {
        mRegisterView.onLoginSucces(message);
    }

    @Override
    public void onFailure(String message) {
        mRegisterView.onLoginFailure(message);
    }

    @Override
    public void LoginUser(Activity context,String email,String password) {

        mLoginView.LoginUser(context,email,password);

    }
}