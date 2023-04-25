package States
import Builder

class WaitForInputState(override var builder: Builder) : State {
    override fun work() {
        println("Wait For Input State: waiting...")
    }
}