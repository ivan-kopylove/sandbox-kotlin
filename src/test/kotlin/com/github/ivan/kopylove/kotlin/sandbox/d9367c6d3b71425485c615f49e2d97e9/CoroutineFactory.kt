package com.github.ivan.kopylove.kotlin.sandbox.d9367c6d3b71425485c615f49e2d97e9

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object CoroutineFactory {
    fun buildCoroutine(): suspend CoroutineScope.() -> Unit =
        {   //GlobalScope means this coroutine part is attached to the whole application lifecycle
            val threadName = Thread.currentThread().name
            println("printed on thread $threadName")
        }

    fun buildLazyJob(): Job {
        return GlobalScope.launch(block = buildCoroutine(), start = LAZY)
    }


}