class Builder {
    var input: MutableList<Boolean> = mutableListOf()

    fun add_input(x: Boolean)
    {
        input.add(x);
    }
    fun getList(): MutableList<Boolean>
    {
        return input;
    }
}