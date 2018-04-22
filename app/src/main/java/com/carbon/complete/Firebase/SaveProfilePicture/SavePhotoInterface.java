package com.carbon.complete.Firebase.SaveProfilePicture;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by archlinux on 3/7/18.
 */

public interface SavePhotoInterface {

    interface View {
        void onAddPhotoSuccess(String message);

        void onAddPhotoFailure(String message);
    }

    interface Presenter {
        void addPhoto(FirebaseUser firebaseUser, Bitmap bitmap);
    }

    interface Interactor {
        void addProfilePictureToDatabase(FirebaseUser firebaseUser, Bitmap bitmap);
    }

    interface OnProfilePictureAddedDatabaseListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

}
