package com.github.ivan.kopylove.kotlin.sandbox.a9c1937bd9b54721bc218e994bc38358

class RoundRobinCallSplitter {

    private var roundRobin = false

    @Synchronized
    fun get(): Boolean {
        val state = roundRobin
        roundRobin = roundRobin.not()
        return state
    }
}