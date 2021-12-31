package com.carbon.complete.Firebase.SaveProfilePicture;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by archlinux on 3/7/18.
 */

public interface PhotoInterface {

    interface View {
        void onAddPhotoSuccess(String message);

        void onAddPhotoFailure(String message);
    }

    interface Presenter {
        void getPhoto(FirebaseUser user);
        void addPhoto(FirebaseUser firebaseUser, Bitmap bitmap);
    }

    interface Interactor {
        void getProfilePictureFromDatabase(FirebaseUser user);
        void addProfilePictureToDatabase(FirebaseUser firebaseUser, Bitmap bitmap);
    }

    interface OnProfilePictureAddedDatabaseListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

    interface OnProfilePictureRequestListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

}
