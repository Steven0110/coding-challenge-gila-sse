package com.gerardo_steven.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String deviceToken;
    private String[] subscribedTopics;
    private String[] channels;
    
    public User(){

    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("deviceToken")
    public String getDeviceToken() {
        return deviceToken;
    }

    @JsonProperty("deviceToken")
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @JsonProperty("subscribed")
    public String[] getSubscribedTopics() {
        return subscribedTopics;
    }

    @JsonProperty("subscribed")
    public void setSubscribedTopics(String[] subscribedTopics) {
        this.subscribedTopics = subscribedTopics;
    }

    @JsonProperty("channels")
    public String[] getChannels() {
        return channels;
    }

    @JsonProperty("channels")
    public void setChannels(String[] channels) {
        this.channels = channels;
    }


    public boolean isSubscribedToTopic(String topic) {
        for (String subscribedTopic : this.subscribedTopics) {
            if (subscribedTopic.equals(topic)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSMS() {
        for (String channel : this.channels) {
            if (channel.equals("sms")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPush() {
        for (String channel : this.channels) {
            if (channel.equals("push")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEmail() {
        for (String channel : this.channels) {
            if (channel.equals("email")) {
                return true;
            }
        }
        return false;
    }
}
