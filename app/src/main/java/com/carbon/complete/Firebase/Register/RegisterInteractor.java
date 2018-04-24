package com.carbon.complete.Firebase.Register;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.carbon.complete.ADTs.User;
import com.carbon.complete.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterInteractor implements RegisterContract.Interactor {
    private static final String TAG = RegisterInteractor.class.getSimpleName();

    private RegisterContract.OnRegistrationListener mOnRegistrationListener;

    public RegisterInteractor(RegisterContract.OnRegistrationListener onRegistrationListener) {
        this.mOnRegistrationListener = onRegistrationListener;
    }

    @Override
    public void performFirebaseRegistration(Activity activity, final String email, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (!task.isSuccessful()) {
                            mOnRegistrationListener.onFailure(task.getException().getMessage());
                        } else {
                            // Add the user to users table.


                            DatabaseReference database= FirebaseDatabase.getInstance().getReference();
                            User user = new User(task.getResult().getUser().getUid(), task.getResult().getUser().getEmail(),null);
                            database.child(Constants.DatabaseTerms.USERS).child(user.uid).setValue(user);
                            database.child(Constants.DatabaseTerms.USERS).child(user.uid).child(Constants.DatabaseTerms.DATA).child(Constants.DatabaseTerms.PROFILE_PICTURE).setValue(Constants.DatabaseTerms.NULL);
                            mOnRegistrationListener.onSuccess("User Registered in Database");


                        }
                    }
                });
    }
}
