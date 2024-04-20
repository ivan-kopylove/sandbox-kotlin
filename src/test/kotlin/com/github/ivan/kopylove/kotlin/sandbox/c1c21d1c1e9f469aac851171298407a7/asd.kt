package com.github.ivan.kopylove.kotlin.sandbox.c1c21d1c1e9f469aac851171298407a7

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.util.concurrent.Executors.newFixedThreadPool

class JunitAsyncAssertion {
    @Test
    fun `should fail when run into a side thread`() {

        // when
        val threadPool = newFixedThreadPool(50)
        val submit = threadPool.submit {
            fail("2")
        }
        submit.get()

        Thread.sleep(5000)

        fail("1")
    }
}