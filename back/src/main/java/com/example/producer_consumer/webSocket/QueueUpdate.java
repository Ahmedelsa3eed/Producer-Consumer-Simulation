package com.example.producer_consumer.webSocket;

public class QueueUpdate {
    private int id;
    private int size;
    private String type;

    public QueueUpdate() {
    }
    public QueueUpdate(int id, int size) {
        this.id = id;
        this.size = size;
        this.type = "Q";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
