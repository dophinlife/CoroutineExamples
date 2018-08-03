package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.cancelChildren
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.CoroutineContext

fun numbersFrom(context:CoroutineContext, start:Int)= produce(context) {
    var x = start
    while(true)send(x++)
}

fun filter(context: CoroutineContext, numbers:ReceiveChannel<Int>, prime:Int)=produce(context) {
    for (x in numbers) if (x % prime != 0) send(x)
}

fun main(args: Array<String>) = runBlocking {
    var cur = numbersFrom(kotlin.coroutines.experimental.coroutineContext, 2)
    for (i in 1..10) {
        val prime=cur.receive()
        println(prime)
        cur= filter(kotlin.coroutines.experimental.coroutineContext, cur, prime)
    }
    kotlin.coroutines.experimental.coroutineContext.cancelChildren()
}