package com.mirego.rebelchat.controllers;

import android.content.Context;

public interface MessageController {

    interface SendMessageCallback {
        void onSendMessageSuccess();
        void onSendMessageFail();
    }

    void sendMessage(Context context, String userId, String text, String image, SendMessageCallback sendMessageCallback);
    void getMessages(Context context, String userId, SendMessageCallback sendMessageCallback);
}
