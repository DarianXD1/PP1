class Implementor {
    fun work(l: MutableList<Boolean>) {
        var result=true
        l.forEach {
            result=result && it
        }
        println("Result: " + result)
    }
}