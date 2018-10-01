package com.nuzharukiya.itsanouk.models;

import org.json.JSONObject;

/**
 * Created by Nuzha Rukiya on 18/10/01.
 */
public class ChatModel {

    private String text;
    private String time;

    ChatModel(ChatBuilder chatBuilder) {
        this.text = chatBuilder.text;
        this.time = chatBuilder.time;
    }

    public static JSONObject getJSONBody(String message) {
        return null;
    }

    public static class ChatBuilder {

        private String text;
        private String time;

        public ChatBuilder(String text, String time) {
            this.text = text;
            this.time = time;
        }

        public ChatModel build() {
            return new ChatModel(this);
        }

        public ChatBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public ChatBuilder setTime(String time) {
            this.time = time;
            return this;
        }
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }
}
