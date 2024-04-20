package com.github.ivan.kopylove.kotlin.sandbox.cd7f1a051ca843ec8bc97697ca7b94a6

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.random.Random

class ChainedCoroutineFrameworkTest {

    @Test
    fun `should do something when something`() {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }

        runBlocking(context = exceptionHandler) {
            val job1 = launch(start = LAZY) {

                while (isActive) {
                    println("repeatable is active")
                    delay(2000)
                    if (Random.nextBoolean()) {
                        throw ArithmeticException()
                    }
                }
            }
            val job2 = launch(context = exceptionHandler, start = LAZY) {
                job1.start()
                println("running job 2")
                delay(2000)
                if (Random.nextBoolean()) {
                    throw IllegalArgumentException()
                }
                job1.cancel()
            }
            job2.join()
        }
    }
}