package com.github.ivan.kopylove.kotlin.sandbox.b3f17ca50f1c456eb2714e283466f0c4

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * page 131
 * Babic, Kordic, Srivastava -- Kotlin Coroutines by Tutorials -- 2022
 */
@OptIn(DelicateCoroutinesApi::class)
class CoroutineExceptionsTest {

    @Test
    fun `should demonstrate exception inside a launch coroutine`() {
        runBlocking {
            // when
            val launchJob = GlobalScope.launch {
                println("1. Exception created via launch coroutine")
                throw IndexOutOfBoundsException()
            }

            launchJob.join()
        }
    }


    @Test
    fun `should demonstrate an exception thrown by async coroutine`() {
        runBlocking {
            val deferredAsync = GlobalScope.async {
                // when
                println("Exception created via async coroutine")
                throw ArithmeticException()
            }

            assertThrows<ArithmeticException> {
                deferredAsync.await()
                println("4. Unreachable, this statement is never executed")
            }
        }
    }

}