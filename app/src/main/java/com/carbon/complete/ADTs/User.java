package com.carbon.complete.ADTs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by archlinux on 3/7/18.
 */

public class User {
    public String email;
    public String uid;
    public String profile_picture;
    private String Base64Picture;




    public User(@NonNull String uid, @NonNull String email, @Nullable String profile_picture) {
        this.uid = uid;
        this.email= email;
        this.profile_picture = profile_picture;
        this.Base64Picture = "";

    }
    public User(@NonNull String base64Picture){

        this.Base64Picture = base64Picture;


    }





}
