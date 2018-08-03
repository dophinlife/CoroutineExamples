package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.cancelChildren
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.coroutineContext

suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}

fun main(args: Array<String>)= runBlocking {
    val channel = Channel<String>()
    launch(coroutineContext){
        sendString(channel, "foo", 200L)
    }
    launch(coroutineContext){
        sendString(channel, "BAR", 500L)
    }
    repeat(6) { // receive first six
        println(channel.receive())
    }
    coroutineContext.cancelChildren()
}