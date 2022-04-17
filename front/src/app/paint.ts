import { PaintState } from "src/PaintingStates/PaintState"

export class Paint{

    private state!: PaintState

    getState(): PaintState { return this.state }

    setState(state: PaintState) { this.state = state }

    draw() {
        return this.state.draw()
    }
    seti(i:number){
      return this.state.seti(i)
    }
}
