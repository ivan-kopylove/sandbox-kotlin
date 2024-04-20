package com.github.ivan.kopylove.kotlin.sandbox.b5c9ff97111748e9a9afb0d5066ad0eb

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
class OrEmptyOnNull {

    @Test
    fun `should do something when something`() {
        // given
        var aString: String? = ""
        aString = null

        // when
        val result = aString.orEmpty()

        // then
        fail("try to check yourself before running the assertion")
        assertThat(result, equalTo(""))
    }
}