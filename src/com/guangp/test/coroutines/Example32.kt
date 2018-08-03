package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun produceNumbersLocal()=produce{
    var x =1
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun launchProcessor(id:Int, channel:ReceiveChannel<Int>)=launch{
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}

fun main(args: Array<String>)= runBlocking {
    val producer= produceNumbersLocal()
    repeat(5) { launchProcessor(it, producer)}
    delay(950)
    producer.cancel()
    println()
}