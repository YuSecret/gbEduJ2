package ru.gb.chat.server.core;

import ru.gb.chat.common.Library;
import ru.gb.network.SocketThread;
import ru.gb.network.SocketThreadListener;

import java.net.Socket;

public class ClientThread extends SocketThread {
    private String nickname;
    private boolean isAuthorized;

    public ClientThread(SocketThreadListener listener, String name, Socket socket) {
        super(listener, name, socket);
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    void authAccept(String nickname) {
        isAuthorized = true;
        this.nickname = nickname;
        sendMessage(Library.getAuthAccept(nickname));
    }

    void authFail() {
        sendMessage(Library.getAuthDenied());
        close();
    }

    void msgFormatError(String msg) {
        sendMessage(Library.getMsgFormatError(msg));
        close();
    }


}
