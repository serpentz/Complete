package com.carbon.complete.ADTs;

public class Chat {

    public String senderUid;
    public String receiverUid;
    public String message;
    public String profile_picture_url;
    public long timestamp;

    public Chat(){

    }

    public Chat( String senderUid, String receiverUid, String message, long timestamp, String profile_picture_url){

        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.message = message;
        this.timestamp = timestamp;
        this.profile_picture_url = profile_picture_url;

    }public Chat( String senderUid, String receiverUid, String message, long timestamp){

        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.message = message;
        this.timestamp = timestamp;

    }





}
