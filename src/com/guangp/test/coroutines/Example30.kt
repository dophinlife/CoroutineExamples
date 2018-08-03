package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking

fun produceNumbers() = produce {
    var x = 1
    while (true) send(x++)
}

fun square(numbers: ReceiveChannel<Int>) = produce {
    for (x in numbers) send(x * x)
}

fun main(args: Array<String>) = runBlocking {
    val numbers = produceNumbers()
    val squares = square(numbers)
    for (i in 1..5) println(squares.receive())
    println("Done!")
    squares.cancel()
    numbers.cancel()
    println()
}