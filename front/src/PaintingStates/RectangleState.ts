import Konva from "konva";
import { PaintState } from "./PaintState";
import { HttpClient } from "@angular/common/http";

export class RectangleState implements PaintState {
  constructor(private http: HttpClient) { }
  i = 0
  seti(n: number) {
    this.i = n
  }
  draw() {
    let id: string = "Q" + this.i.toString()
    this.http.get("http://localhost:8080/addQueue", {
      params: {
        id: this.i
      }
    }).subscribe();
    let rec = new Konva.Rect({
      x: 100,
      y: 100,
      fill: 'blue',
      width: 100,
      height: 100,
      id: id,
    })
    console.log(rec)
    return rec
  }

}
