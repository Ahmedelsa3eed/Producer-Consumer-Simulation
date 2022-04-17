package com.example.producer_consumer.queue;

import com.example.producer_consumer.machine.IObservable;
import com.example.producer_consumer.shared.Product;

public interface IProducer {
    Product produceProduct(IObservable observable);
}
