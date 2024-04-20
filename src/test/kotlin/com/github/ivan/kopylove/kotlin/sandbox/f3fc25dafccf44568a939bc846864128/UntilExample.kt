package com.github.ivan.kopylove.kotlin.sandbox.f3fc25dafccf44568a939bc846864128

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
class UntilExample {
    @Test
    fun `should do something when something`() {
        // when
        val intRange = 2 until 10

        // then
        fail("try to check yourself before running the assertion")
        assertThat(intRange.last(), equalTo(9))
    }
}