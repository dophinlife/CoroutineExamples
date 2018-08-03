package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.coroutineContext

fun main(args: Array<String>)= runBlocking {
    val request= launch {
        repeat(3) { i ->
            launch(coroutineContext) {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    request.join()
    println("Now processing of the request is complete")
}