package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * Created by guangp on 2018/8/2.
 */
fun main(args: Array<String>) = runBlocking {
    val job = launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join()
}