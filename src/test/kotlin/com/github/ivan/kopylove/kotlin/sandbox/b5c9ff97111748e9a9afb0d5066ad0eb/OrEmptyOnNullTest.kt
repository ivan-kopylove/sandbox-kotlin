package com.github.ivan.kopylove.kotlin.sandbox.b5c9ff97111748e9a9afb0d5066ad0eb

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class OrEmptyOnNullTest {

    @Test
    fun `should be able to return empty string even an object is null`() {
        // then
        assertThat(null.orEmpty(), equalTo(""))
    }
}