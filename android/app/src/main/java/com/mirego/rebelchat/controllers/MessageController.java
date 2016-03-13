package com.mirego.rebelchat.controllers;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Response;

public interface MessageController {

    interface SendMessageCallback {
        void onSendMessageSuccess();
        void onSendMessageFail();
    }

    interface GetMessagesCallback {
        void onSendMessageSuccess(Response response) throws IOException, JSONException;
        void onSendMessageFail();
    }

    void sendMessage(Context context, String userId, String text, String image, SendMessageCallback sendMessageCallback);
    void getMessages(Context context, String userId, GetMessagesCallback getMessagesCallback);
}
