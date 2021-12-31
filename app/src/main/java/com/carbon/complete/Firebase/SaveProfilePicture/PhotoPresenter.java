package com.carbon.complete.Firebase.SaveProfilePicture;

import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by archlinux on 3/7/18.
 */

public class PhotoPresenter implements PhotoInterface.Presenter, PhotoInterface.OnProfilePictureAddedDatabaseListener, PhotoInterface.OnProfilePictureRequestListener{
    private PhotoInterface.View mRegisterView;
    private PhotoInteractor mPhotoInteractor;

    public PhotoPresenter(PhotoInterface.View registerView) {
        this.mRegisterView = registerView;
        mPhotoInteractor = new PhotoInteractor(this);
    }

    @Override
    public void getPhoto(FirebaseUser user) {

    }

    @Override
    public void addPhoto(FirebaseUser firebaseUser, Bitmap bitmap) {
        mPhotoInteractor.addProfilePictureToDatabase(firebaseUser,bitmap);
    }

    @Override
    public void onSuccess(String message) {
        mRegisterView.onAddPhotoSuccess(message);

    }

    @Override
    public void onFailure(String message) {
        mRegisterView.onAddPhotoFailure(message);

    }
}
