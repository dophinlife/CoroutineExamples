package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

fun somethingUsefulOneAsync()= async {
    doSomethingUsefulOne()
}

fun somethingUsefulTwoAsync()= async {
    doSomethingUsefulTwo()
}

fun main(args: Array<String>) {
    val time= measureTimeMillis {
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}