package com.carbon.complete.Firebase.SaveProfilePicture;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by archlinux on 3/7/18.
 */

public class SavePhotoPresenter implements SavePhotoInterface.Presenter, SavePhotoInterface.OnProfilePictureAddedDatabaseListener{
    private SavePhotoInterface.View mRegisterView;
    private SavePhotoInteractor mSavePhotoInteractor;

    public SavePhotoPresenter(SavePhotoInterface.View registerView) {
        this.mRegisterView = registerView;
        mSavePhotoInteractor = new SavePhotoInteractor(this);
    }
    @Override
    public void addPhoto(FirebaseUser firebaseUser, Bitmap bitmap) {
        mSavePhotoInteractor.addProfilePictureToDatabase(firebaseUser,bitmap);
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
