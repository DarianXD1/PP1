import java.io.File
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

class HistoryLogRecord:Comparable<HistoryLogRecord> {
    var timestamp : Date
    var command : String

    constructor(t: Date, c :String)
    {
        timestamp = t
        command = c
    }
/*Funcția compareTo()
 primește un obiect other de tip HistoryLogRecord și returnează un Int
 care reprezintă rezultatul comparației între obiectul curent (pe care este apelată funcția) și obiectul other.*/
    override fun compareTo(other: HistoryLogRecord): Int {
        return timestamp.compareTo(other.timestamp)
    }
}

fun <T : Comparable<T>> max(first: T, second: T): T {
    return if (first >= second)
        first
    else second
}

/*fun
 <HistoryLogRecord : Comparable<HistoryLogRecord>> max (first: HistoryLogRecord, second: HistoryLogRecord): HistoryLogRecord {
    val k = first.compareTo(second)
    return if (k >= 0)
        first
    else
        second
}*/

fun <T : Comparable<T>> replace(src: T, rpl: T, map: MutableMap<Date, T>){
    map.forEach {
        if(it.value == src) {
            map[it.key] = rpl
        }
    }
}

fun main()
{
    var nr = 0
    var timestamp: java.util.Date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-01-01 00:00:00")
    var date: String = ""
    var command: String = ""
    var list= mutableListOf<HistoryLogRecord>()
    var map= mutableMapOf<Date,HistoryLogRecord>()

    val inputStream: InputStream = File("history.log").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    lineList.forEach{

        if(nr < 50)
        {
            if(it.contains("Start-Date:")) {
                nr += 1
                var line = it.split(" ")
                date = line[1] + " " + line[3]
                timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)
            }
            if(it.contains("Commandline:")){
                var line = it.split(":")
                command = line[1]
                list.add(HistoryLogRecord(timestamp, command))
            }
        }
    }

    list.forEach {
        map[it.timestamp] = it
    }
    println(map)

    //Maximul dintre primele doua elemente
    var elem0 = list.get(0)
    var elem1 = list.get(1)
    var maxim = max<HistoryLogRecord>(elem0,elem1)
    println(elem0.timestamp)
    println(elem1.timestamp)
    println(maxim.timestamp)

    //Functia de cautare si inlocuire
    replace<HistoryLogRecord>(elem0, elem1, map)
    println(map)
}

