package com.carbon.complete.Fragments.Chat;



import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.carbon.complete.ADTs.Chat;
import com.carbon.complete.Utils.Constants;
import com.carbon.complete.Firebase.Chat.ChatContract;
import com.carbon.complete.Firebase.Chat.ChatPresenter;
import com.carbon.complete.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class ChatFragment extends Fragment implements ChatContract.View, TextView.OnEditorActionListener, View.OnClickListener {
    private RecyclerView mRecyclerViewChat;
    private EditText mETxtMessage;
    private Button btn_send;
    private View view;


    private ProgressDialog mProgressDialog;


    private ChatRecyclerAdapter mChatRecyclerAdapter;

    private ChatPresenter mChatPresenter;

    public static ChatFragment newInstance(String receiverUid, String senderUid, String profile_picture) {
        Bundle args = new Bundle();
        args.putString(Constants.DatabaseTerms.RECEIVER_UID, receiverUid);
        args.putString(Constants.DatabaseTerms.SENDER_UID, senderUid);
        args.putString(Constants.DatabaseTerms.PROFILE_PICTURE, profile_picture);
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_chat, container, false);
        bindViews(fragmentView);
        view = fragmentView;


        return fragmentView;
    }

    private void bindViews(View view) {
        mRecyclerViewChat = view.findViewById(R.id.reyclerview_message_list);
        mETxtMessage =  view.findViewById(R.id.editText_chatbox);
        btn_send = view.findViewById(R.id.button_chatbox_send);
        btn_send.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {

        mChatPresenter = new ChatPresenter(this);
        mChatPresenter.getMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(),
                getArguments().getString(Constants.DatabaseTerms.RECEIVER_UID));
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            sendMessage();
            return true;
        }
        return false;
    }

    private void sendMessage() {
        String message = mETxtMessage.getText().toString();
        String receiverUid = getArguments().getString(Constants.DatabaseTerms.RECEIVER_UID);
        String  profile_picture_url = getArguments().getString(Constants.DatabaseTerms.PROFILE_PICTURE);
        String senderUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        long timestamp = System.currentTimeMillis();

        Chat chat = new Chat(
                senderUid,
                receiverUid,message,timestamp,
                profile_picture_url
                );





       mChatPresenter.sendMessage(chat);
    }

    @Override
    public void onSendMessageSuccess() {
        mETxtMessage.setText("");
        Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendMessageFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetMessagesSuccess(Chat chat) {


        if (mChatRecyclerAdapter == null) {
            mChatRecyclerAdapter = new ChatRecyclerAdapter(new ArrayList<Chat>());
            mRecyclerViewChat.setAdapter(mChatRecyclerAdapter);
            mRecyclerViewChat.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        mChatRecyclerAdapter.add(chat);


    //    mRecyclerViewChat.smoothScrollToPosition(mChatRecyclerAdapter.getItemCount() - 1);
    }

    @Override
    public void onGetMessagesFailure(String message) {
      Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v){

        switch(v.getId()){

            case R.id.button_chatbox_send: sendMessage(); break;


        }



    }


}
