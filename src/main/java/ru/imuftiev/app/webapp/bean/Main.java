package ru.imuftiev.app.webapp.bean;

import java.io.Serializable;

public class Main implements Serializable {

    private boolean trigger;

    private String message;

    public Main() {
    }

    public Main(boolean trigger, String message) {
        this.trigger = trigger;
        this.message = message;
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
