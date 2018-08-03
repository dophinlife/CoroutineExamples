package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.coroutineContext

fun main(args: Array<String>) = runBlocking {
    val job = Job()
    val coroutines=List(10){i->
        launch(coroutineContext, parent = job) {
            delay((i + 1) * 200L)
            println("Coroutine $i is done")
        }
    }
    println("Launched ${coroutines.size} coroutines")
    delay(500L)
    println("Cancelling the job!")
    job.cancelAndJoin()
}