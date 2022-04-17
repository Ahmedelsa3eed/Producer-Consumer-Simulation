package com.example.producer_consumer.controller;

public class Clone implements Cloneable{

    @Override
    protected Object clone()throws CloneNotSupportedException{
        return super.clone();

    }
}
