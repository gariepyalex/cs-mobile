package com.mirego.rebelchat.models;

public class Message {
    public final int id;
    public final String text;
    public final String from;

    public Message(int id, String text, String from) {
        this.id = id;
        this.text = text;
        this.from = from;
    }
}
