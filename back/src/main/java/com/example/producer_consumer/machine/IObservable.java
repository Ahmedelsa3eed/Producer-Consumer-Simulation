package com.example.producer_consumer.machine;

import com.example.producer_consumer.queue.IObserver;

public interface IObservable {
    void register(IObserver observer);
    void unRegister(IObserver observer);
    void notifyObserver();
}
