package com.github.ivan.kopylove.kotlin.sandbox.df54bb21eded4a4ab71618c8907779a7

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test

/**
 * Babic, Kordic, Srivastava -- Kotlin Coroutines by Tutorials -- 2022
 */
@OptIn(DelicateCoroutinesApi::class)
class CoroutineBasicExamples {

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun `should fire and forget huge number of coroutines without OutOfMemoryException`() {
        // when
        (1..100_000).forEach {
            GlobalScope.launch {   //GlobalScope means this coroutine part is attached to the whole application lifecycle
                val threadName = Thread.currentThread().name
                println("$it printed on thread $threadName")
            }
        }
        Thread.sleep(2000) //give a time to execute all coroutines
    }

    @Test
    fun `should delay`() {

        // when
        GlobalScope.launch {
            println("Hello coroutine!")
            delay(500)
            println("Right back at ya!")
        }
        Thread.sleep(1000)
    }

    /**
     * questions before execution
     * - what will be printed?
     * - when the job1 will be started in fact?
     */
    @Test
    fun `should run 2 jobs with a specific order`() {
        // when
        val job1 = GlobalScope.launch(start = CoroutineStart.LAZY) {
            delay(200)
            println("Pong")
            delay(200)
        }
        GlobalScope.launch {
            delay(200)
            println("Ping")
            job1.join()
            println("Ping")
            delay(200)
        }
        Thread.sleep(1000)
    }

    @Test
    fun `should show parent-child relation`() {
        with(GlobalScope) {
            val parentJob = launch {
                delay(200)
                println("I’m the parent")
                delay(200)
            }
            launch(context = parentJob) {
                delay(200)
                println("I’m a child")
                delay(200)
            }
            if (parentJob.children.iterator().hasNext()) {
                println("The Job has children!")
            } else {
                println("The Job has NO children")
            }
            Thread.sleep(1000)
        }
    }

    @Test
    fun `should demonstrate retry-logic mechanism`() {
        var isDoorOpen = false
        println("Unlocking the door... please wait.\n")
        GlobalScope.launch {
            delay(3000)
            isDoorOpen = true
        }
        GlobalScope.launch {
            repeat(4) {
                println("Trying to open the door...\n")
                delay(800)
                if (isDoorOpen) {
                    println("Opened the door!\n")
                } else {
                    println("The door is still locked\n")
                }
            }
        }
        Thread.sleep(5000)
    }
}