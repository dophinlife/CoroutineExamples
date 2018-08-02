package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.*

/**
 * Created by guangp on 2018/8/3.
 */
fun main(args: Array<String>) {
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("I'm running finally")
                    delay(1000L)
                    println("And I've just delayed for 1 second because I'm non-cancellable")
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }
}