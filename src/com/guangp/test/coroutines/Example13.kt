package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeout
import kotlinx.coroutines.experimental.withTimeoutOrNull

/**
 * Created by guangp on 2018/8/3.
 */
fun main(args: Array<String>) {
    runBlocking {
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done"
        }
        println("Result is $result")
    }
}