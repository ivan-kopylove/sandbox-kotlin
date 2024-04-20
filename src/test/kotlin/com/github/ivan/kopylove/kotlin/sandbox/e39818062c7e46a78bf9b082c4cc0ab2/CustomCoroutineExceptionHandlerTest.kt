package com.github.ivan.kopylove.kotlin.sandbox.e39818062c7e46a78bf9b082c4cc0ab2

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

@OptIn(DelicateCoroutinesApi::class)
class CustomCoroutineExceptionHandlerTest {

    @Test
    fun `should catch an exception by custom exception handler passed into a launch coroutine`() {
        // when
        runBlocking {
            val myCustomExceptionHandler = CoroutineExceptionHandler { _,
                                                                       exception ->
                println("Caught $exception")
            }

            val job = GlobalScope.launch(context = myCustomExceptionHandler, block = {
                throw AssertionError("My Custom Assertion Error!")
            })

            job.join()
        }
    }

    @Test
    fun `should catch catch an exception passed into runBlocking`() {
        // when
        val exceptionHandler = CoroutineExceptionHandler { _,
                                                           exception ->
            println("Caught $exception")
        }

        runBlocking(context = exceptionHandler) {
            val job = GlobalScope.launch(context = exceptionHandler, block = {
                throw AssertionError("My Custom Assertion Error!")
            })

            job.join()
        }
    }

    @Test
    fun `should catch an exception of a lazy coroutine`() {
        // when
        val myCustomExceptionHandler = CoroutineExceptionHandler { _,
                                                                   exception ->
            println("Caught $exception")
        }

        runBlocking(context = myCustomExceptionHandler) {
            val job = GlobalScope.launch(start = LAZY, context = myCustomExceptionHandler, block = {
                throw AssertionError("My Custom Assertion Error!")
            })

            job.join()
        }
    }


    @Test
    fun `should have no effect when registering coroutine into async coroutines`() {
        // when
        runBlocking {
            val exceptionHandler = CoroutineExceptionHandler { _,
                                                               exception ->
                println("Caught $exception") // is not printed
            }
            val deferred = GlobalScope.async(context = exceptionHandler, block = {
                throw ArithmeticException()
            })
            deferred.join()
        }
    }
}