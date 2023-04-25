class AND_GATE(override val implementor: Implementor, override val builder: Builder) : Gate {
    override fun calculate()
    {
        implementor.work(builder.getList());
    }
}