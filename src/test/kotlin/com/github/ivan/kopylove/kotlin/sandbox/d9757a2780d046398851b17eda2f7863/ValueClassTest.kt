package com.github.ivan.kopylove.kotlin.sandbox.d9757a2780d046398851b17eda2f7863

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test


@JvmInline
value class Password(private val s: String)

class ValueClassTest {
    @Test
    fun `should be serialized `() {
        // then
        assertThat(Password("123"), equalTo("Password(s=123)"))
    }
}