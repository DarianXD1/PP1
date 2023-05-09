import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.File
import java.util.*

val files: Queue<String> = LinkedList(listOf("file1.txt", "file2.txt"))

fun printLine(fileName: String) = File(fileName).forEachLine { println(it) }

class SimpleThread : Thread() {
    override fun run() {
        printLine(files.remove())
        println("Instanta clasei derivate din Thread-ul ${Thread.currentThread()} s-a executat.")
    }
}

fun corutina() = runBlocking {
    val rezultat = async {
        printLine(files.remove())
        return@async "Corutina s-a terminat."
    }
    println(rezultat.await())
}


fun main() {
    object : Thread() {
        override fun run() {
            println("Sunt in thread-ul singleton: ${currentThread()}")
        }
    }.start()

    //Threads
    val thread1 = SimpleThread()
    val thread2 = SimpleThread()
    thread1.start()
    thread2.start()
    val thread = Thread {
        println("Thread lambda ${Thread.currentThread()} s-a executat.")
    }
    thread.start()

    //Corutine
    //corutina()
    //corutina()

}
