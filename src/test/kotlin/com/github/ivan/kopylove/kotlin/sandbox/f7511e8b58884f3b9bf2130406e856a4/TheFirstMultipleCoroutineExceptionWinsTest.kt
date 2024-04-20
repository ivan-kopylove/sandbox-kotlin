package com.github.ivan.kopylove.kotlin.sandbox.f7511e8b58884f3b9bf2130406e856a4

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

@OptIn(DelicateCoroutinesApi::class)
class TheFirstMultipleCoroutineExceptionWinsTest {
    @Test
    fun `the first occurred exception should win`() {
        runBlocking {
            val myCustomExceptionHandler = CoroutineExceptionHandler { _, exception ->
                println("Caught $exception with suppressed${exception}")
            }
            val parentJob = GlobalScope.launch(myCustomExceptionHandler) {
                // child job 1
                launch {

                        throw ArithmeticException()

                }
                // child job 2
                launch {
                    delay(100)
                    throw IllegalStateException()
                }
                // Delaying the parentJob
                delay(Long.MAX_VALUE)
            }
            // Wait until parentJob completes
            parentJob.join()
        }
    }
}