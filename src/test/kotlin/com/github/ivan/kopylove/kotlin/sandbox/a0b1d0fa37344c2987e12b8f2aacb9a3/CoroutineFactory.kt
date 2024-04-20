package com.github.ivan.kopylove.kotlin.sandbox.a0b1d0fa37344c2987e12b8f2aacb9a3

import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object CoroutineFactory {
    fun buildLazyJob(): Job {
        return GlobalScope.launch(block = {
            println("delayed job started")
        }, start = LAZY)
    }


}