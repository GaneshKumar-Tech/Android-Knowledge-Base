import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Start")

    launch {
        delay(1000)
        println("Coroutine finished")
    }

    println("End")
}