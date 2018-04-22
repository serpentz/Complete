package com.carbon.complete.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.carbon.complete.ADTs.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by archlinux on 4/9/18.
 */

public class SharedPrefHelper {

    private static SharedPreferences sharedPreferences;
    private static final String USER_LIST = "user_list";
    private static Context ctz;


   public  SharedPrefHelper(Context ctz){
       this.ctz = ctz;
       sharedPreferences= ctz.getSharedPreferences(USER_LIST,0);

    }
    private void putUser(String key, List<User> data){
       SharedPreferences.Editor editor = sharedPreferences.edit();

       editor.putString(key,data.toString());
       editor.apply();
    }


}
