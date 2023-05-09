import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*

val alpha = 5
val numbers: Queue<Int> = LinkedList<Int>(listOf(4, 7, 10, 14, 18))
var sorted: List<Int> = listOf()

class SimpleThread_1 : Thread() {
    override fun run() {
        var size = numbers.size
        while (size > 0) {
            val n = numbers.remove()
            size--
            numbers.add(n * alpha)
        }
        println("Thread 1 ended")
    }
}

class SimpleThread_2 : Thread() {
    override fun run() {
        sleep(500)
        sorted = numbers.sorted()
        println("Thread 2 ended")
    }
}

class SimpleThread_3 : Thread() {
    override fun run() {
        sleep(1000)
        var size = sorted.size
        var i = 0;
        while (i < size) {
            val n = sorted[i]
            i++
            println("$n")
        }
        println("Thread 3 ended")
    }
}

fun corutine() = runBlocking {
    val multiply = async {
        var size = numbers.size
        while (size > 0) {
            val n = numbers.remove()
            size--
            numbers.add(n * alpha)
        }
        return@async "Multiplying completed."
    }
    println(multiply.await())

    val sort = async {
        sorted = numbers.sorted()
        return@async "Sorting completed."
    }
    println(sort.await())

    val print = async {
        var size = sorted.size
        var i = 0;
        while (i < size) {
            val n = sorted[i]
            i++
            println("$n")
        }
        return@async "Printing completed."
    }
    println(print.await())

}

fun main() {
    //Threads
    val t1 = SimpleThread_1()
    val t2 = SimpleThread_2()
    val t3 = SimpleThread_3()
    t1.start()
    t2.start()
    t3.start()

    //Corutine
    //corutine() //erori Caused by: java.lang.ClassNotFoundException: kotlin.KotlinNothingValueException
}

