package com.carbon.complete.Firebase.SaveProfilePicture;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;


import com.carbon.complete.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;


/**
 * Created by archlinux on 3/7/18.
 */

public class SavePhotoInteractor implements SavePhotoInterface.Interactor {

     private String TAG  = SavePhotoInteractor.class.getSimpleName();

    private SavePhotoInterface.OnProfilePictureAddedDatabaseListener mOnUserDatabaseListener;
    private FirebaseDatabase database;

    public SavePhotoInteractor(SavePhotoInterface.OnProfilePictureAddedDatabaseListener onUserDatabaseListener) {
        this.mOnUserDatabaseListener = onUserDatabaseListener;
    }

    @Override
    public void addProfilePictureToDatabase(final FirebaseUser firebaseUser, Bitmap bitmap) {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = FirebaseStorage.getInstance().getReference().child(firebaseUser.getUid()).child(Constants.DatabaseTerms.PROFILE_PICTURE).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                mOnUserDatabaseListener.onFailure(exception.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

                mOnUserDatabaseListener.onSuccess("Photo Added");

               AddPathToDatabase(firebaseUser, taskSnapshot.getDownloadUrl().toString());

            }
        });


    }

    private void AddPathToDatabase(FirebaseUser user, String uri) {

        database = FirebaseDatabase.getInstance();

        database.getReference()
                .child(Constants.DatabaseTerms.USERS)
                .child(user.getUid())
                .child(Constants.DatabaseTerms.DATA)
                .child(Constants.DatabaseTerms.PROFILE_PICTURE)
                .setValue(uri)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {



                    }
        });

    }


}
