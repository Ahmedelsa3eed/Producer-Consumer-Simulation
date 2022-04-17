# Producer-Consumer_Simulation  
## Accosiated with Alexandria University
## [Video Link](https://www.youtube.com/watch?v=Gt9EfFbd-h8)

## Objective
The aim of the project is to develop a simulation program to simulate a production line as a queuing network.

## UML Diagram
![uml](https://user-images.githubusercontent.com/73740339/163721883-24e6a76e-4b02-43b1-b08d-06741a62bbf2.png)

## Used Design Patterns

### Singleton DP:
- We have applied the singleton design pattern on the “SingletonDiagram” class, it is unique for the simulation that running. It has arrays of machines, queues and threads which we use.
### Snapshot DP:
- We have also used a snapshot design pattern to store our diagram in the caretaker history.
### Observable DP:
- We applied the observable design pattern on the machine and the queue, we treat the machine here as the observable and it has an array of observables which are the queues here. When the machine is free it notifies its observers to update their state, they send the product to the machine if they have.
### Producer Consumer DP:
- Producer supplies objects used by the Consumer and places them in the queue. Production and consumption of objects are asynchronous.
Queue holds produced objects that cannot be consumed immediately.
Consumer -machine- pulls from the Queue and uses the object produced by the Producer. If the Queue is empty it waits.

## User Guide

![image](https://user-images.githubusercontent.com/73740339/163722574-2ece8e04-4e86-4ab1-8c0d-dfaf474ea862.png)

- Click on Machine button to insert a machine to the layer.
- Click on Queue button to insert a queue to our layer.
- You can click on each of them and drag.

![image](https://user-images.githubusercontent.com/73740339/163722647-2ce290cf-eab4-499c-9e2d-8e0cbf2cd5ea.png)

- You can clear the layer by clicking on clear button.
- To connect the components, you specify the from input field which you want to start the connection from.
- Write M+( machine id) or M+( queue id) eg.(M0, Q4).

![image](https://user-images.githubusercontent.com/73740339/163722702-315aaccd-0bd9-4265-8e7a-cab90f90f6c3.png)

- Enter the number of products you want to start the simulation with.
- Then click on the play button to start the simulation.
