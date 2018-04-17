package com.carbon.complete.Firebase.GetAllUsers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.carbon.complete.ADTs.User;
import com.carbon.complete.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by archlinux on 3/25/18.
 */

public class GetAllUsersInteractor implements GetAllUsersContract.Interactor {

    GetAllUsersContract.OnGetUserListener listener;
    List<User> users = new ArrayList<>();
    // User bob = new User("uid", "email", "https://firebasestorage.googleapis.com/v0/b/complete-01.appspot.com/o/p54MIw985ldfw0g7A8WqQU7W53E3%2Fprofile_picture?alt=media&token=b9873172-bd27-4f37-a34d-7db57913ae48");
    HashMap<String, User> user_map = new HashMap<>();




    public GetAllUsersInteractor(GetAllUsersContract.OnGetUserListener listener) {
        this.listener = listener;

//        users.add(0, bob);
//        user_map.put(bob.uid, bob);

    }

    @Override
    public void getAllUsers(String flags) {

        FirebaseDatabase.getInstance().getReference().child(Constants.DatabaseTerms.USERS).addValueEventListener(new ValueEventListener() {

            User user;
       int i = 0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot documentSnapshot : dataSnapshot.getChildren()) {

                    DataSnapshot doc = documentSnapshot;

                    if(doc!=null)
                    if (!user_map.containsKey(doc.child("uid").getValue().toString())) {

                        if(CheckResult(doc)) {

                            user = new User(doc.child("uid").getValue().toString(), doc.child("email").getValue().toString(), doc.child("data").child("profile_picture").getValue().toString());
                            users.add(0, user);
                            user_map.put(user.uid, user);
                        }

                    }
                }
                listener.onSuccess(users);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(databaseError.getMessage());

            }
        });
    }

    private boolean CheckResult(DataSnapshot doc) {

        if(doc.child("uid").getValue()== null){
            return false;
        }
        if(doc.child("data").child("profile_picture").getValue() == null){
            return false;
        }
        if(doc.child("email").getValue() == null){

            return false;
        }

        return true;
    }
}
