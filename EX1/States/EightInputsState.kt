package States
import AND_GATE
import Builder
import Implementor

class EightInputsState(override var builder: Builder) : State {
    override fun work() {
        print("Eight Inputs State: ")
        var and_gate = AND_GATE(Implementor(), builder)
        and_gate.calculate()
    }
}