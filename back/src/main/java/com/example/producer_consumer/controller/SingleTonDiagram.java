package com.example.producer_consumer.controller;

import com.example.producer_consumer.machine.Machine;
import com.example.producer_consumer.queue.QueueOfProducts;
import com.example.producer_consumer.shared.Product;
import com.example.producer_consumer.webSocket.SendUpdateService;

import java.util.ArrayList;
import java.util.List;

public class SingleTonDiagram extends Clone{

    private static SingleTonDiagram diagram;
    private List<Machine> machines;
    private List<QueueOfProducts> queues;
    private List<Thread> threads;
    private SendUpdateService sendUpdate;
    private int numberOfProducts;
    public SingleTonDiagram diagramClone;



    public SingleTonDiagram getDiagramClone() {
        return diagramClone;
    }

    public void setDiagramClone(SingleTonDiagram diagramClone) {
        this.diagramClone = diagramClone;
    }

    private SingleTonDiagram(){
        this.machines=new ArrayList<Machine>();
        this.queues=new ArrayList<QueueOfProducts>();
        this.threads=new ArrayList<>();
    }

    public static SingleTonDiagram getInstance(){
        if (diagram == null){
            synchronized (SingleTonDiagram.class){
                diagram = new SingleTonDiagram();
            }
        }
        return diagram;
    }

    public void addMachine(Machine machine) {
        this.machines.add(machine);
    }
    public void removeMachine(int machineId) {
        this.machines.remove(machineId);
    }
    public void addQueue(QueueOfProducts queue) {
        this.queues.add(queue);
    }
    public void removeQueue(int queueId) {
        this.queues.remove(queueId);
    }
    public List<Machine> getMachines() {
        return machines;
    }
    public List<QueueOfProducts> getQueues() {
        return queues;
    }
    public int getNumberOfQueues() {
        return queues.size();
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }
    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public SendUpdateService getSendUpdate() {
        return sendUpdate;
    }

    public void setSendUpdate(SendUpdateService sendUpdate) {
        this.sendUpdate = sendUpdate;
    }

    //            M <- q
    public void connectQueueToMachine(int machineId, int queueId){
        QueueOfProducts tmpQ = null;
        for(QueueOfProducts q : queues){
            if(q.getId() == queueId){
                tmpQ = q;
            }
        }
        for(Machine m : machines){
            if(m.getId() == machineId){
                m.register(tmpQ);
                System.out.println("tmpQ : "+ tmpQ.getId());
            }
        }
    }

    //  q <- M
    public void connectMachineToQueue(int machineId, int queueId){
        Machine tmpM = null;
        for(Machine m : machines){
            if(m.getId() == machineId){
                m.setOutputQueueId(queueId);
                tmpM = m;
            }
        }
        for(QueueOfProducts q : queues){
            if(q.getId() == queueId){
                q.setConnectionFrom(tmpM);
            }
        }
    }

    public static void resetDiagram(){
        diagram = new SingleTonDiagram();
    }

    public void simulate(int numOfProducts){
        for (Machine machine: diagram.machines){
            Thread th = new Thread(machine);
            machine.setOnState(true);
            threads.add(th);
        }
        for (Thread thread: threads){
            thread.start();
        }
    }

    public void replay(){

    }


}
