import java.util.*

fun main()
{
    val reader = Scanner(System.`in`)

    val automat= Automat()
    var builder=Builder()

    var bool: Boolean=true
    var nr_of_inputs=0;

    while(nr_of_inputs<8) {

        print("Enter input( 1=True, 0=False): ")
        var integer: Int = reader.nextInt()
        nr_of_inputs++
        if (integer == 1)
            bool = true
        else if (integer == 0)
            bool = false

        builder.add_input(bool)
        automat.updateState(builder)
        automat.work()
    }
}
