package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Created by guangp on 2018/8/3.
 */
fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two= doSomethingUsefulTwo()
        println("The answer is ${one+two}")
    }
    println("Completed in $time ms")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    return 29
}