package com.example.producer_consumer;

import com.example.producer_consumer.controller.SingleTonDiagram;
import com.example.producer_consumer.machine.Machine;
import com.example.producer_consumer.queue.QueueOfProducts;
import com.example.producer_consumer.shared.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerConsumerApplication {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(ProducerConsumerApplication.class, args);
//        Machine m0 = new Machine();
//        m0.setId(0);
//        Machine m1 = new Machine();
//        m1.setId(1);
//        Machine m2 = new Machine();
//        m2.setId(2);
//
//        QueueOfProducts q0 = new QueueOfProducts();
//        q0.setId(0);
//        q0.add(new Product());
//        q0.add(new Product());
//        q0.add(new Product());
//        QueueOfProducts q1 = new QueueOfProducts();
//        q1.setId(1);
//        QueueOfProducts q2 = new QueueOfProducts();
//        q2.setId(2);
//
//        SingleTonDiagram diagram = SingleTonDiagram.getInstance();
//        diagram.addMachine(m0);
//        diagram.addMachine(m1);
//        diagram.addMachine(m2);
//        diagram.addQueue(q0);
//        diagram.addQueue(q1);
//        diagram.addQueue(q2);
//
//        diagram.connectQueueToMachine(0, 0);
//
//        diagram.connectMachineToQueue(1,0);
//
//        diagram.connectQueueToMachine(1, 1);
//        diagram.connectQueueToMachine(2, 1);
//
//        diagram.connectMachineToQueue(2,1);
//        diagram.connectMachineToQueue(2,2);
//
//        diagram.simulate();
//
//        Thread.sleep(6000);
//        System.out.println(q2.productsNumber());
    }

}
