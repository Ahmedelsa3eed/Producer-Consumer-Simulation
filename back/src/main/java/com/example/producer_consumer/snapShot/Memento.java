package com.example.producer_consumer.snapShot;

import com.example.producer_consumer.machine.Machine;
import com.example.producer_consumer.queue.QueueOfProducts;

import javax.crypto.Mac;

/*
    Memento contains state of an
    object to be restored.
*/
public class Memento {

    private Machine machine;
    private QueueOfProducts queueOfProducts;

    public Memento(Machine machine ,QueueOfProducts queueOfProducts){
       this.machine=machine;
       this.queueOfProducts=queueOfProducts;

    }
    public Machine getMachine() {
        return machine;
    }
    public QueueOfProducts getQueueOfProducts(){

        return queueOfProducts;
    }
}
