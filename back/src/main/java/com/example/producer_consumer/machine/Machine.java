package com.example.producer_consumer.machine;

import com.example.producer_consumer.controller.SingleTonDiagram;
import com.example.producer_consumer.queue.IObserver;
import com.example.producer_consumer.queue.QueueOfProducts;
import com.example.producer_consumer.shared.Product;
import com.example.producer_consumer.webSocket.MachineUpdate;
import com.example.producer_consumer.webSocket.QueueUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Machine implements IObservable, IConsumer, Runnable{
    private int id;
    private String color;
    private boolean isFree;
    private boolean onState;
    private int serviceTime;
    private int outputQueueId;
    private Product outputProduct;
    private boolean isConsumerExist;
    private List<QueueOfProducts> observers;

    public Machine(int id){
        Random rand = new Random();
        observers = new ArrayList<>();
        serviceTime = rand.nextInt(1500) + 500;
        isConsumerExist = false;
        color = "green";
        isFree = false;
        this.id = id;
    }
    public int getOutputQueueId() {
        return outputQueueId;
    }
    public void setOutputQueueId(int outputQueueId) {
        isConsumerExist = true;
        this.outputQueueId = outputQueueId;
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
    public boolean isOnState() {
        return onState;
    }
    public void setOnState(boolean onState) {
        this.onState = onState;
    }

    /**
        check the queue if waiting products need to be consumed,
        if not then it need to register itself to the queue as ready
     */
    @Override
    public void consumeProduct(){
        //poll from the queue put it in the machine
        Product p = null;
        for (QueueOfProducts observer: observers){
            p = observer.produceProduct(this);
            if(p == null)
                continue;
            else
                break;
        }
        outputProduct = p;
    }
    @Override
    public void register(IObserver observer) {
        System.out.println(((QueueOfProducts) observer).getId());
        observers.add((QueueOfProducts) observer);
    }
    @Override
    public void unRegister(IObserver observer) {
        int observerIndex = observers.indexOf(observer);
        System.out.println("Observer " + (observerIndex + 1) + " deleted");
        observers.remove(observerIndex);
    }
    @Override
    public void notifyObserver() {
        // Cycle through all observers and notifies them
        for (IObserver observer : observers)
            observer.update(this);
    }
    @Override
    public void run() {
        while(onState){
            notifyObserver();
            try {
                System.out.println("machine "+id+" starts");
                if(outputProduct == null)
                    this.setColor("green");
                else
                    this.setColor(outputProduct.getColor());
                SingleTonDiagram diagram = SingleTonDiagram.getInstance();
                int queueSize = 0;
                for (QueueOfProducts queue: diagram.getQueues()){
                    if (outputProduct != null && queue.getId() == this.outputQueueId){
                        queue.add(outputProduct);
                        queueSize = queue.size();
                        diagram.getSendUpdate()
                                .send(new MachineUpdate(outputProduct.getColor(), id));
                        System.out.println("output queue from m"+id+": "+queue.size());
                    }
                }
                Thread.sleep(serviceTime);
                this.setColor("green");
                diagram.getSendUpdate()
                        .send(new MachineUpdate(color, id));
                diagram.getSendUpdate()
                        .send(new QueueUpdate(outputQueueId, diagram.getQueues().get(outputQueueId).size()));
                if(diagram.getQueues().get(diagram.getNumberOfQueues()-1).size() == diagram.getNumberOfProducts()){

                    diagram.getSendUpdate()
                            .send(new QueueUpdate(0,0));
                    //SingleTonDiagram.resetDiagram();
                    break;
                }
                System.out.println("machine "+id+" finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
