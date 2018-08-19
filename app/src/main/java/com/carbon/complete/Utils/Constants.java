package com.carbon.complete.Utils;

/**
 * Created by archlinux on 4/25/18.
 */

import android.os.Environment;

/**
 * Created by archlinux on 3/1/18.
 */

public class Constants {

    public static String PATH_TO_PICTURES = "/DatingApp/media/Dating App Images";
    public static String FULL_PATH_TO_PICTURES = Environment.getExternalStorageDirectory().getPath()+ PATH_TO_PICTURES;
    public static String PATH_TO_INTERNAL_MEMORY = Environment.getRootDirectory().getPath();

    public static class DatabaseTerms {

        public static final String NULL = "null";
        public static final java.lang.String NAME = "name" ;


        public static String USER = "user";
        public static String EMAIL = "email";
        public static String USERS = "users";

        public static String UID = "uid";
        public static String TOKEN = "token";

        public static String DATA = "data";
        public static String PROFILE_PICTURE = "profile_picture";

        public static String DEVICE = "device";
        public static String PERSONAL = "personal";


        public static final String CHAT_ROOMS = "chat_rooms";

        public static final String RECEIVER_UID = "receiverUid";
        public static final String SENDER_UID = "senderUid";

    }
}