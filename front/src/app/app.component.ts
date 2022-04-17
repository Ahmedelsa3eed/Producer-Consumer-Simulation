import { Circle } from 'konva/lib/shapes/Circle';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Layer } from 'konva/lib/Layer';
import { Stage } from 'konva/lib/Stage';
import { CircleState } from 'src/PaintingStates/CircleState';
import { RectangleState } from 'src/PaintingStates/RectangleState';
import { Paint } from './paint';
import { TextCircleState } from "./TextCircleState";
import { Group } from "konva/lib/Group";
import { TextRectangleState } from "./TextRectangleState";
import { NgForm } from "@angular/forms";
import Konva from "konva";
import { WebSocketAPI } from './WepSocketAPI';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'producerConsumerSimulation';
  webSocketAPI!: WebSocketAPI;
  responseUpdate: any
  name!: string
  drawingState: Paint = new Paint
  boxs: any[] = []
  connections = []
  stage!: Stage
  layer!: Layer
  counterMachine: number = -1
  counterQueue: number = -1

  constructor(private http: HttpClient) { }
  ngOnInit(): void {
    this.webSocketAPI = new WebSocketAPI(this)
    this.webSocketAPI._connect()
    this.stage = new Stage({
      height: window.innerHeight,
      width: window.innerWidth,
      container: 'konva-holder'
    })
    this.layer = new Layer()
    this.stage.add(this.layer)
  }

  simulate() {
    let productsNumber = (<HTMLInputElement>document.getElementById("num")).value;
    this.http.get(`http://localhost:8080/simulate/${productsNumber}`)
      .subscribe();
    console.log(productsNumber)
  }
  replay() {
    this.http.get("http://localhost:8080/replay").subscribe();
  }
  queueState() {
    this.counterQueue++
    let group = new Group({
      draggable: true,
      id: "q" + this.counterQueue.toString()
    })
    this.drawingState.setState(new RectangleState(this.http))
    this.drawingState.seti(this.counterQueue)
    group.add(this.drawingState.draw())
    group.add(new TextRectangleState().draw(this.counterQueue))
    this.layer.add(group)
  }

  machineState() {
    this.counterMachine++
    let group = new Group({
      draggable: true,
      id: "m" + this.counterMachine.toString()
    })
    this.drawingState.setState(new CircleState(this.http))
    this.drawingState.seti(this.counterMachine)
    group.add(this.drawingState.draw())
    group.add(new TextCircleState().draw(this.counterMachine))
    this.layer.add(group)
  }

  connectionState(logInForm: NgForm) {
    let from = logInForm.value.from
    let to = logInForm.value.to
    if (!((from[0] == "M" && to[0] == "Q") || (from[0] == "Q" && to[0] == "M"))) {
      return
    }
    let ff = from.substring(1, from.length)
    let f: number = +ff
    console.log(f)
    let tt = to.substring(1, to.length)
    let t: number = +tt
    console.log(t)
    if ((from[0] == "M" && f > this.counterMachine) || (from[0] == "Q" && f > this.counterQueue)
      || (to[0] == "M" && t > this.counterMachine) || (to[0] == "Q" && t > this.counterQueue)) {
      return
    }
    if (isNaN(f) || isNaN(t))
      return

    if (to == "Q0") {
      return
    }
    // if(from=="Q"+this.counterQueue){
    //   return
    // }
    if (from[0] == 'M') {
      this.http.get("http://localhost:8080/connectMachineToQueue", {
        params: {
          machineId: f,
          queueId: t
        }
      }).subscribe();
    }
    if (from[0] == 'Q') {
      this.http.get("http://localhost:8080/connectQueueToMachine", {
        params: {
          queueId: f,
          machineId: t
        }
      }).subscribe();
    }
    let x = "#" + from
    let gx = "#" + from
    gx = gx.toLowerCase()
    let y = "#" + to
    let gy = "#" + to
    gy = gy.toLowerCase()
    var machine = this.stage.findOne(x)
    var queue = this.stage.findOne(y)
    // console.log(this.stage.findOne(gx).parent?.children[1].parent?.attrs.text)

    let x1 = 100, y1 = 100, x2 = 100, y2 = 100
    if (machine.parent?.attrs.x != null)
      x1 = machine.parent?.attrs.x + 100
    if (machine.parent?.attrs.y != null)
      y1 = machine.parent?.attrs.y + 100
    if (queue.parent?.attrs.x != null)
      x2 = queue.parent?.attrs.x + 100
    if (queue.parent?.attrs.y != null)
      y2 = queue.parent?.attrs.y + 100
    this.stage.findOne(gx).draggable(false)
    this.stage.findOne(gy).draggable(false)
    let line = new Konva.Arrow({
      points: [x1, y1, x2, y2],
      stroke: 'red',
      strokeWidth: 3,
      pointerLength: 10,
      pointerWidth: 10,
    });
    this.layer.add(line)
  }

  clear() {
    this.http.get("http://localhost:8080/clear").subscribe();
    this.layer.destroy()
    this.layer = new Layer()
    this.stage.add(this.layer)
    this.counterMachine = -1
    this.counterQueue = -1
  }
  connect() {
    this.webSocketAPI._connect();
  }
  disconnect() {
    this.webSocketAPI._disconnect();
  }
  handleMessage(update: any) {
    console.log(update)
    //deal with it
    var obj = JSON.parse(update as string);
    console.log(obj.type)
    if (obj.type === "M") {
      let x = "#M" + obj.id
      let machine: Circle = this.stage.findOne(x)
      machine.fill(obj.color)
    } else {
      let x = "#qq" + obj.id
      this.stage.findOne(x).setAttr('text', 'Q'+obj.id+'\nsize: '+obj.size).setAttr('fontSize', 28)
      console.log(obj.size, "size")
    }
  }
  
}
