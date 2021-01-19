package com.example.demo.model;

public class MessageUtil {
    private String message;

    public MessageUtil(String message) {
        this.message = message;
    }

    public String printMessage(){
        System.out.println(message);
        return message;
    }

    public String salutationMessage(){
        this.message = "tutorialspoint" + message;
        System.out.println(message);
        return message;
    }

    public String exitMessage(){
        this.message = "www."+ message;
        System.out.println(message);
        return message;
    }
}
