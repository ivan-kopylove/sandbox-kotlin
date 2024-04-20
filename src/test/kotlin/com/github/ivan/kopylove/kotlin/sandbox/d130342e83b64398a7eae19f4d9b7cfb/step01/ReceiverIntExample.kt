package com.github.ivan.kopylove.kotlin.sandbox.d130342e83b64398a7eae19f4d9b7cfb.step01

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

/**
 * For refreshing: try to rename sum.
 *
 * Click so that idea is highlighted using IDE coded semantic.
 *
 * https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver
 */
class ReceiverIntExample {
    @Test
    fun `should extend a class and have access to _this_ inside lambda body`() {
        // given
        val sum: Int.(Int) -> Int = { abc -> this.plus(abc) }

        // when
        val res = 1.sum(2)

        // then
        fail("try to check yourself before running the assertion")
        assertThat(res, equalTo(3))
    }

    @Test
    fun `another syntax of the same feature`() {
        // given
        val sum = fun Int.(other: Int): Int = this + other

        // when
        val result = 2.sum(3)

        // then
        fail("try to check yourself before running the assertion")
        assertThat(result, equalTo(5))
    }
}