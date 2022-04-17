package com.example.producer_consumer.controller;

import com.example.producer_consumer.machine.Machine;
import com.example.producer_consumer.queue.QueueOfProducts;
import com.example.producer_consumer.shared.Product;
import com.example.producer_consumer.webSocket.SendUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class Controller {
    SingleTonDiagram diagram;
    private SimpMessagingTemplate SMTemplate;
    @Autowired
    public Controller(SimpMessagingTemplate SMTemplate){
        SendUpdateService sendUpdate = new SendUpdateService(SMTemplate);
        diagram = SingleTonDiagram.getInstance();
        diagram.setSendUpdate(sendUpdate);
    }
    @GetMapping("/addMachine")
    public void addMachine(@RequestParam int id){
        System.out.println("add machine");
        diagram = SingleTonDiagram.getInstance();
        diagram.addMachine(new Machine(id));
    }
    @GetMapping("/addQueue")
    public void addQueue(@RequestParam int id){
        System.out.println("add queue");
        diagram = SingleTonDiagram.getInstance();
        diagram.addQueue(new QueueOfProducts(id));
    }
    @GetMapping("/removeMachine/{machineId}")
    public void removeMachine(@PathVariable("machineId") int machineId){
        System.out.println("remove machine");
        diagram = SingleTonDiagram.getInstance();
        diagram.removeMachine(machineId);
    }
    @GetMapping("/connectMachineToQueue")
    public void connectMachineToQueue(@RequestParam int machineId, int queueId){
        System.out.println(machineId+" " + " "+queueId);
        diagram = SingleTonDiagram.getInstance();
        diagram.connectMachineToQueue(machineId, queueId);
    }
    @GetMapping("/connectQueueToMachine")
    public void connectQueueToMachine(@RequestParam int queueId, int machineId){
        System.out.println(machineId+" " + " "+queueId);
        diagram = SingleTonDiagram.getInstance();
        diagram.connectQueueToMachine(machineId, queueId);
    }
    @GetMapping("/simulate/{productsNumber}")
    public void simulate(@PathVariable("productsNumber") int productsNumber){
        System.out.println(productsNumber);
        diagram = SingleTonDiagram.getInstance();
        diagram.setNumberOfProducts(productsNumber);
        for (int i=0; i<productsNumber; i++)
            diagram.getQueues().get(0).add(new Product());
        diagram.simulate(productsNumber);
    }
    @GetMapping("/clear")
    public void clear(){
        SingleTonDiagram.resetDiagram();
    }

    @GetMapping("/replay")
    public void replay(){
        SingleTonDiagram diagram=SingleTonDiagram.getInstance();
        diagram = diagram.diagramClone;
        diagram.simulate(diagram.getNumberOfProducts());
    }
}
