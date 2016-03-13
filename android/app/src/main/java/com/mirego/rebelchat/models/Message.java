package com.mirego.rebelchat.models;

public class Message {
    public final String id;
    public final String text;
    public final String from;

    public Message(String id, String text, String from) {
        this.id = id;
        this.text = text;
        this.from = from;
    }
}
