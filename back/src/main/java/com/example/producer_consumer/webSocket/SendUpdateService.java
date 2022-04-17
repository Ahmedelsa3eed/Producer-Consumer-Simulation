package com.example.producer_consumer.webSocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.converter.GsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class SendUpdateService {
    private final SimpMessagingTemplate template;
    private Gson gson;

    @Autowired
    public SendUpdateService(SimpMessagingTemplate SMTemplate){
        template = SMTemplate;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    public void send(MachineUpdate machineUpdate){
        System.out.println("sending machine update ..");
        GsonMessageConverter mc = new GsonMessageConverter(gson);
        template.setMessageConverter(mc);
        template.convertAndSend("/update", machineUpdate);
    }
    public void send(QueueUpdate queueUpdate){
        System.out.println("sending queue update ..");
        GsonMessageConverter mc = new GsonMessageConverter(gson);
        template.setMessageConverter(mc);
        template.convertAndSend("/update", queueUpdate);
    }
}