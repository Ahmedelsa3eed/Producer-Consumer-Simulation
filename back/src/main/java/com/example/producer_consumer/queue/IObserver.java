package com.example.producer_consumer.queue;

import com.example.producer_consumer.machine.IObservable;
import com.example.producer_consumer.machine.Machine;

public interface IObserver {
     void update(Machine machine);
}
