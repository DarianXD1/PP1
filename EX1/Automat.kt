import States.*

class Automat {
    lateinit var state: State

    fun updateState(builder: Builder)
    {
        var nr = builder.getList().size
        when (nr) {
            0 -> state= WaitForInputState(builder)
            1 -> state= WaitForInputState(builder)
            2 -> state= TwoInputsState(builder)
            3 -> state= ThreeInputsState(builder)
            4 -> state= FourInputsState(builder)
            5 -> state= WaitForInputState(builder)
            6 -> state= WaitForInputState(builder)
            7 -> state= WaitForInputState(builder)
            8 -> state= EightInputsState(builder)
            else -> {
                println("Too many inputs")
            }
        }
    }

    fun work()
    {
        state.work()
    }
}