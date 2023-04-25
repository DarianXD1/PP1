interface Gate {
    val builder: Builder
    val implementor: Implementor
    fun calculate();
}