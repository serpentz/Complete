package com.carbon.complete.Fragments.Users;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.carbon.complete.ADTs.User;
import com.carbon.complete.ChatActivity;
import com.carbon.complete.Firebase.GetAllUsers.GetAllUsersContract;
import com.carbon.complete.Firebase.GetAllUsers.GetAllUsersPresenter;
import com.carbon.complete.Fragments.Chat.ChatFragment;
import com.carbon.complete.R;
import com.carbon.complete.Utils.ItemClickSupport;


import java.util.List;


public class UsersFragment extends Fragment implements GetAllUsersContract.View, SwipeRefreshLayout.OnRefreshListener,ItemClickSupport.OnItemClickListener{


    private static final String ARG_COLUMN_COUNT = "column-count";

    private String TAG = UsersFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private View view;
    private GetAllUsersPresenter presenter;
    private MyUserRecyclerViewAdapter mUsersAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UsersFragment() {
    }


    @SuppressWarnings("unused")
    public static UsersFragment newInstance(int columnCount) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_list, container, false);

             context = view.getContext();
             init();

      return view;
    }

    /**
     * Method that initializes after the view is created and a reference is made.
     * Initialized with a Big(O) = ln(n)^2 *3
     ** @return void
     */

    private void init() {
        context = view.getContext();
        presenter = new GetAllUsersPresenter(this);
        recyclerView = view.findViewById(R.id.recyclerView_all_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_all_users);
        presenter.getAllUsers("");

                swipeRefreshLayout.setRefreshing(true);
                swipeRefreshLayout.setOnRefreshListener(this);
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(this);
        recyclerView.setLayoutFrozen(false);


    }

    @Override
    public void OnUserGetSuccess(List<User> users) {

        mUsersAdapter  =new MyUserRecyclerViewAdapter(users);

               swipeRefreshLayout.setRefreshing(false);

            recyclerView.setAdapter(mUsersAdapter);

    }

    @Override
    public void OnUserGetFailure(String message) {

        Log.e(TAG, message);

    }


    /**
     * Swipe Refresh Override
     */
    @Override
    public void onRefresh() {
      presenter.getAllUsers(null);
      swipeRefreshLayout.setRefreshing(false);
    }

    /**
     *
     * Item Click support Override
     *
     * @param recyclerView
     * @param position
     * @param v
     */

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

setNotClickable();
        ChatActivity.startActivity( getContext(),
                mUsersAdapter.getUser(position).uid,
                mUsersAdapter.getUser(position).email,
                mUsersAdapter.getUser(position).profile_picture);




    }

    private void setNotClickable() {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                Log.e(TAG, " touch event " );

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

}
