import Konva from "konva";

export class TextCircleState {

  draw(i:number) {
    return new Konva.Text({
      x: 65,
      y: 70,
      text: 'M'+i,
      fontFamily: 'Calibri',
      fontSize: 50,
      padding: 5,
      fill: 'black',
    })
  }

}
