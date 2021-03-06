package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.cancelChildren
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select

suspend fun selectAorB(a: ReceiveChannel<String>, b: ReceiveChannel<String>): String =
        select {
            a.onReceiveOrNull { value ->
                if (value == null)
                    "Channel 'a' is closed"
                else
                    "a -> '$value'"
            }
            b.onReceiveOrNull { value ->
                if (value == null)
                    "Channel 'b' is closed"
                else
                    "b -> '$value'"
            }
        }

fun main(args: Array<String>) = runBlocking<Unit> {
    // we are using the context of the main thread in this example for predictability ...
    val a = produce(coroutineContext) {
        repeat(4) { send("Hello $it") }
    }
    val b = produce(coroutineContext) {
        repeat(4) { send("World $it") }
    }
    repeat(8) { // print first eight results
        println(selectAorB(a, b))
    }
    coroutineContext.cancelChildren()
}