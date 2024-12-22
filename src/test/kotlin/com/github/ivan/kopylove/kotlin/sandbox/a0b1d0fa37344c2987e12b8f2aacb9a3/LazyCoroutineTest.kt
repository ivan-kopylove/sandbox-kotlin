package com.github.ivan.kopylove.kotlin.sandbox.a0b1d0fa37344c2987e12b8f2aacb9a3

import org.junit.jupiter.api.fail
import com.github.ivan.kopylove.kotlin.sandbox.a0b1d0fa37344c2987e12b8f2aacb9a3.CoroutineFactory.buildLazyJob
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.junit.jupiter.api.fail

class LazyCoroutineTest {
    @Test
    fun `should start job once _join_ is called`() {
        // given
        runBlocking {
            val lazyJob = buildLazyJob()

            // when
            fail("try to check yourself before running the assertion")
            assertThat(lazyJob.isActive, equalTo(false))
            lazyJob.join()

            // then
            fail("try to check yourself before running the assertion")
            assertThat(lazyJob.isCompleted, equalTo(true))
        }
    }

    @Test
    fun `should start job once _start_ is called`() {
        // given
        runBlocking {
            val lazyJob = buildLazyJob()

            // when
            fail("try to check yourself before running the assertion")
            assertThat(lazyJob.isActive, equalTo(false))
            fail("try to check yourself before running the assertion")
            assertThat(lazyJob.start(), equalTo(true))

            lazyJob.invokeOnCompletion {
                // then
                println("lazy job is completed")
                fail("try to check yourself before running the assertion")
                assertThat(lazyJob.isCompleted, equalTo(true))
            }
        }
    }

}