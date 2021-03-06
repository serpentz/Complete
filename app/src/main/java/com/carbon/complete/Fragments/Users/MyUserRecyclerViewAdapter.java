package com.carbon.complete.Fragments.Users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.carbon.complete.ADTs.User;


import com.carbon.complete.Utils.Constants;
import com.carbon.complete.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyUserRecyclerViewAdapter extends RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;


    public MyUserRecyclerViewAdapter(List<User> items) {
        mValues = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).email);
        holder.mContentView.setText(mValues.get(position).uid);

        if(!mValues.get(position).profile_picture.equals(Constants.DatabaseTerms.NULL))
        Picasso.get().load(mValues.get(position).profile_picture).into(holder.mImageView);
        else
            Picasso.get().load(R.drawable.shape_of_view).into(holder.mImageView);



    }
    public User getUser(int position) {
        return mValues.get(position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final CircularImageView mImageView;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.user_list_item_id);
            mIdView = view.findViewById(R.id.user_list_item_email);
            mImageView = view.findViewById(R.id.user_list_item_imageview);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
