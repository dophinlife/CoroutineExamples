package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * Created by guangp on 2018/8/2.
 */
fun main(args: Array<String>) {
    launch {
        delay(1000L)
        println("World!")
    }

    println("Hello,")
    Thread.sleep(2000L)
}