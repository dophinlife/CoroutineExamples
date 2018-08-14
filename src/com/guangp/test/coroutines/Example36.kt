package com.guangp.test.coroutines

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.system.measureTimeMillis

suspend fun massiveRun(context: CoroutineContext, action: suspend () -> Unit) {
    val n = 1000
    val k = 1000
    val time = measureTimeMillis {
        val jobs = List(n) {
            launch(context) {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} actions in $time ms")
}

val mutex = Mutex()
var counter = 0
val mtContext = newFixedThreadPoolContext(1, "mtPool")
val counterContext = newSingleThreadContext("CounterContext")
var counterAtomic = AtomicInteger()
fun main(args: Array<String>) = runBlocking {
    // Thread-safe data structures
    massiveRun(CommonPool) {
        counterAtomic.incrementAndGet()
    }
    println("Counter = ${counterAtomic.get()}")

    // fine-grained
    massiveRun(CommonPool) { // run each coroutine in CommonPool
        withContext(counterContext) { // but confine each increment to the single-threaded context
            counter++
        }
    }
    println("Counter = $counter")

    //  coarse-grained
    // 减少了 context 切换，所以比较快
    massiveRun(counterContext) {
        counter++
    }
    println("Counter = $counter")


    // Mutual exclusion
    massiveRun(CommonPool) {
        mutex.withLock {
            counter++
        }
    }
    println("Counter = $counter")
}