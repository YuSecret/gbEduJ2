package ru.gb.chat.server.core;

public interface ChatServerListener {
    void onChatServerMessage(ChatServer server, String msg);
}
