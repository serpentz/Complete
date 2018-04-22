package com.carbon.complete.Firebase.GetAllUsers;

import com.carbon.complete.ADTs.User;

import java.util.List;

/**
 * Created by archlinux on 3/25/18.
 */

public class GetAllUsersPresenter implements GetAllUsersContract.Presenter, GetAllUsersContract.OnGetUserListener {
    private GetAllUsersContract.View mGetAllUsersView;
    private GetAllUsersInteractor mGetAllUsers;

    public GetAllUsersPresenter(GetAllUsersContract.View view) {

        this.mGetAllUsersView = view;

        mGetAllUsers = new GetAllUsersInteractor(this);

    }


    @Override
    public void getAllUsers(String flags) {
        mGetAllUsers.getAllUsers(flags);

    }

    @Override
    public void onSuccess(List<User> users) {

        mGetAllUsersView.OnUserGetSuccess(users);

    }

    @Override
    public void onFailure(String message) {
        mGetAllUsersView.OnUserGetFailure(message);

    }
}
