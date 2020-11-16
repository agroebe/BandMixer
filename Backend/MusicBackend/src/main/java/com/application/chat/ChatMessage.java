package com.application.chat;



public class ChatMessage
{

    public ChatMessage(MessageType type,  String sender){
        this.type = type;
        this.sender = sender;
    }

    private MessageType type;

    private String content;

    private String sender;

    private String time;

    public String getContent(){
        return this.content;
    }

    public String getSender(){
        return this.sender;
    }

    public String getTime(){
        return this.time;
    }

    public MessageType getType(){
        return this.type;
    }
}
