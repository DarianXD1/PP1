package States
import AND_GATE
import Builder
import Implementor

class ThreeInputsState(override var builder: Builder) : State {
    override fun work() {
        print("Three Inputs State: ")
        var and_gate= AND_GATE(Implementor(), builder)
        and_gate.calculate()
    }
}