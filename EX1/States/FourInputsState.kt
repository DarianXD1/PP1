package States
import AND_GATE
import Builder
import Implementor

class FourInputsState(override var builder: Builder) : State {
    override fun work() {
        print("Four Inputs State: ")
        var and_gate = AND_GATE(Implementor(), builder)
        and_gate.calculate()
    }
}