package States
import Builder

interface State {
    var builder: Builder
    fun work()
}