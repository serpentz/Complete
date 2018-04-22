package com.carbon.complete.Firebase.Register;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;


public interface RegisterContract {
    interface View {

        void onRegistrationFailure(String message);
        void onRegistrationSuccess(String message);
    }

    interface Presenter {
        void register(Activity activity, String email, String password);
    }

    interface Interactor {
        void performFirebaseRegistration(Activity activity, String email, String password);
    }

    interface OnRegistrationListener {

        void onSuccess(String message);

        void onFailure(String message);
    }
}
