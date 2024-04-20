package com.github.ivan.kopylove.kotlin.sandbox.dsajdsklad

class RoundRobinCallSplitter {

    private var roundRobin = false

    @Synchronized
    fun get(): Boolean {
        val state = roundRobin
        roundRobin = roundRobin.not()
        return state
    }
}