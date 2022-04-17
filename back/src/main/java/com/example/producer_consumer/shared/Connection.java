package com.example.producer_consumer.shared;

import java.util.ArrayList;
import java.util.List;

public class Connection {
    //from certain queue to one or more machines
    private List<Integer> from;
    private List<Integer> to;

    public Connection(int from, int to) {
        this.from = new ArrayList<>();
        this.from.add(from);
        this.to = new ArrayList<>();
        this.to.add(to);
    }

    public List<Integer> getFrom() {
        return from;
    }

    public List<Integer> getTo() {
        return to;
    }

    public void addConnectionTo(int to){
        this.to.add(to);
    }

    public void addConnectionFrom(int from){ this.from.add(from);}
}
