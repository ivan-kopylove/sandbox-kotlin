package com.github.ivan.kopylove.kotlin.sandbox.a9c1937bd9b54721bc218e994bc38358

import org.awaitility.kotlin.await
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors.newFixedThreadPool
import java.util.concurrent.TimeUnit.SECONDS

class RoundRobinCallSplitterTest {
    @Test
    fun `should distribute evenly under high load`() {
        // given
        val result = ConcurrentLinkedQueue<Boolean>()
        val requests = 1_000_000
        val threadPool = newFixedThreadPool(500)
        val roundRobinCallSplitter = RoundRobinCallSplitter()

        // when
        (1..requests).forEach { _ ->
            threadPool.submit {
                val element = roundRobinCallSplitter.get()
                result.add(element)
            }
        }

        await
            .atMost(5, SECONDS)
            .until { result.size == requests }

        // then
        assertThat(result.count { it }, equalTo(requests / 2))
        assertThat(result.count { it.not() }, equalTo(requests / 2))
    }
}