package com.example.producer_consumer.snapShot;

import java.util.ArrayList;
import java.util.List;

//our history
public class CareTaker {

    private List<Memento> mementos = new ArrayList<Memento>();

    public void add(Memento newMemento){
        mementos.add(newMemento);
    }

    public Memento get(int index){
        return mementos.get(index);
    }
}
