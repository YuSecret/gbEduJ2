package ru.gb.chat.common;

import java.util.ArrayList;

public class Library {
    /*
    * IRC (Internet Relay Chat)
    * delimiter = §
    * /auth_request§login§password
    * /auth_accept§nickname
    * /auth_error
    * /broadcast§message
    * /msg_format_error
    * /user_list§user1§user2§user3.....
    * /cbcast§msg
    * */
    public static final String DELIMITER = "§";
    public static final String AUTH_REQUEST = "/auth_request";
    public static final String AUTH_ACCEPT = "/auth_accept";
    public static final String AUTH_DENIED = "/auth_denied";
    // если мы вдруг не поняли, что за сообщение и не смогли разобрать
    public static final String MSG_FORMAT_ERROR = "/msg_format_error";
    // то есть сообщение, которое будет посылаться всем
    public static final String TYPE_BROADCAST = "/bcast";
    public static final String USER_LIST = "/user_list";
    public static final String TYPE_CLIENT_BCAST = "/cbcast";

    public static String getClientBcast(String msg) {
        return TYPE_CLIENT_BCAST + DELIMITER + msg;
    }

    public static String getUserList(String users) {
        return USER_LIST + DELIMITER + users;
    }

    public static String getAuthRequest(String login, String password) {
        return AUTH_REQUEST + DELIMITER + login + DELIMITER + password;
    }

    public static String getAuthAccept(String nickname) {
        return AUTH_ACCEPT + DELIMITER + nickname;
    }

    public static String getAuthDenied() {
        return AUTH_DENIED;
    }

    public static String getMsgFormatError(String message) {
        return MSG_FORMAT_ERROR + DELIMITER + message;
    }

    public static String getTypeBroadcast(String src, String message) {
        return TYPE_BROADCAST + DELIMITER + System.currentTimeMillis() +
                DELIMITER + src + DELIMITER + message;
    }

}
