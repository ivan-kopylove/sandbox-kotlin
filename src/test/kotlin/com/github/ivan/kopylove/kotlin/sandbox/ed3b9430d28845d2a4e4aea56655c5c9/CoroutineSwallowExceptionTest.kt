package com.github.ivan.kopylove.kotlin.sandbox.ed3b9430d28845d2a4e4aea56655c5c9

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

@OptIn(DelicateCoroutinesApi::class)
class CoroutineSwallowExceptionTest {

    @Test
    fun `should swallow an exception - this sometimes is desired behaviour`() {
        runBlocking {
            // when
            val deferred = GlobalScope.async {
                println("Throwing exception from async")
                throw ArithmeticException("Something Crashed")
            }
            // deferred.await() is not called and that's why is swallowed
        }
    }


}