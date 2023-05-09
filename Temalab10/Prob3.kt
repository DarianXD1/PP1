import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*


val numbers: Queue<Int> = LinkedList<Int>(listOf(1, 2, 3, 4, 5, 6, 7, 8))

class SimpleThread : Thread() {
    override fun run() {
        val n = numbers.remove()
        var sum = 0
        for (i in 0..n) {
            sum += i
        }
        println("For n = $n  sum calculated by thread is =  $sum")
    }
}

fun corutina() = runBlocking {
    val rezultat = async {
        val n = numbers.remove()
        var sum = 0
        for (i in 0..n) {
            sum += i
        }
        return@async "For n = $n  sum calculated by coroutine is = $sum"
    }
    println(rezultat.await())
}

fun main() {

    //Threads
    val t1 = SimpleThread()
    val t2 = SimpleThread()
    val t3 = SimpleThread()
    val t4 = SimpleThread()
    t1.start()
    t2.start()
    t3.start()
    t4.start()

    //Corutine
    //corutina()
    //corutina()
    //corutina()
    //corutina()
}

