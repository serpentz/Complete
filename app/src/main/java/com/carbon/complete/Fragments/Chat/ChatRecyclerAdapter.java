package com.carbon.complete.Fragments.Chat;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.carbon.complete.ADTs.Chat;
import com.carbon.complete.R;
import com.google.firebase.auth.FirebaseAuth;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ChatRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ME = 1;
    private static final int VIEW_TYPE_OTHER = 2;

    private List<Chat> mChats;
    private CharSequence current_user_id;

    public ChatRecyclerAdapter(List<Chat> chats) {
        mChats = chats; current_user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void add(Chat chat) {
        mChats.add(chat);
        notifyItemInserted(mChats.size() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_ME:
                View viewChatMine = layoutInflater.inflate(R.layout.chat_box_mine, parent, false);
                viewHolder = new MyChatViewHolder(viewChatMine);
                break;
            case VIEW_TYPE_OTHER:
                View viewChatOther = layoutInflater.inflate(R.layout.chat_box_other_person, parent, false);
                viewHolder = new MyChatViewHolder(viewChatOther);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (TextUtils.equals(mChats.get(position).senderUid,
                FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            configureMyChatViewHolder((MyChatViewHolder) holder, position);
        } else {
            configureOtherChatViewHolder((OtherChatViewHolder) holder, position);
        }
    }

    private void configureMyChatViewHolder(MyChatViewHolder myChatViewHolder, int position) {
        Chat chat = mChats.get(position);


        myChatViewHolder.txtChatMessage.setText(chat.message);
    }

    private void configureOtherChatViewHolder(OtherChatViewHolder otherChatViewHolder, int position) {
        Chat chat = mChats.get(position);
        otherChatViewHolder.txtChatMessage.setText(chat.message);
      otherChatViewHolder.timestamp.setText(chat.timestamp + "" );
   Picasso.get().load(chat.profile_picture_url).placeholder(R.drawable.shape_of_view).into(otherChatViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mChats != null) {
            return mChats.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals(mChats.get(position).senderUid,
               current_user_id)) {
            return VIEW_TYPE_ME;
        } else {
            return VIEW_TYPE_OTHER;
        }
    }

    private  class MyChatViewHolder extends RecyclerView.ViewHolder {
        private TextView txtChatMessage;


        public MyChatViewHolder(View itemView) {
            super(itemView);
            txtChatMessage = itemView.findViewById(R.id.text_message_body);

        }
    }

    private  class OtherChatViewHolder extends RecyclerView.ViewHolder {
        private TextView txtChatMessage,timestamp,name;
        private CircularImageView imageView;

        public OtherChatViewHolder(View itemView) {
            super(itemView);
            txtChatMessage =  itemView.findViewById(R.id.other_person_message_body);
            timestamp =  itemView.findViewById(R.id.other_person_message_time);
            imageView = itemView.findViewById(R.id.other_person_icon);

        }
    }
}
