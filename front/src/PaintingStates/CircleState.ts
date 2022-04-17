import { PaintState } from "./PaintState";
import { Circle } from "konva/lib/shapes/Circle";
import { HttpClient } from "@angular/common/http";
import Konva from "konva";

export class CircleState implements PaintState {
  constructor(private http: HttpClient) { }
  i: number = 1
  seti(n: number) {
    this.i = n
  }
  draw() {
    //let stage = new AppComponent().stage
    let id: string = "M" + this.i.toString()
    this.http.get("http://localhost:8080/addMachine", {
      params: {
        id: this.i
      }
    }).subscribe();
    let circle = new Circle({
      x: 100,
      y: 100,
      fill: 'green',
      filters:[Konva.Filters.RGB],
      radius: 60,
      id: id,
    })
    console.log(circle)
    return circle
  }

}
