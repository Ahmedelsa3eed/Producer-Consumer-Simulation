package com.example.producer_consumer.queue;

import com.example.producer_consumer.controller.SingleTonDiagram;
import com.example.producer_consumer.machine.IObservable;
import com.example.producer_consumer.machine.Machine;
import com.example.producer_consumer.shared.Product;
import com.example.producer_consumer.webSocket.QueueUpdate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueOfProducts implements IObserver , IProducer{
    private int id;
    private Queue<Product> products;
    private List<IObservable> machines;//machines that put their output in that queue

    public QueueOfProducts(int id) {
        this.id = id;
        products = new LinkedList<>();
        machines = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public void add(Product product){
        products.add(product);
    }
    public int size(){
        return products.size();
    }

    public void update(Machine machine){
        machine.consumeProduct();
    }
    @Override
    public Product produceProduct(IObservable observable) {
        SingleTonDiagram diagram = SingleTonDiagram.getInstance();
        diagram.getSendUpdate().send(new QueueUpdate(id, products.size()));
        System.out.println("id: " + id + " size: " + products.size());
        return products.poll();
    }

    public void setConnectionFrom(Machine machine) {
        machines.add(machine);
        System.out.println( " queue " + id +" is connected to "+"machine "+machine.getId() );
    }
}
