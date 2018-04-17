package com.carbon.complete.Firebase.GetAllUsers;


import com.carbon.complete.ADTs.User;

import java.util.List;

/**
 * Created by archlinux on 3/25/18.
 */

public interface GetAllUsersContract {


    interface View{

         void OnUserGetSuccess(List<User> users);
         void OnUserGetFailure(String message);

    }
    interface Presenter{

        void getAllUsers(String flags);
    }

    interface Interactor{

        void getAllUsers(String flags);

    }
    interface OnGetUserListener {
        void onSuccess(List<User> users);

        void onFailure(String message);
    }


}
