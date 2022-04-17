package com.example.producer_consumer.webSocket;

public class MachineUpdate {
    private String type;
    private String color;
    private int id;

    public MachineUpdate() {
    }
    public MachineUpdate(String color, int id) {
        this.color = color;
        this.id = id;
        this.type = "M";
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
