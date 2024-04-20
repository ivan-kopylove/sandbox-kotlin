package com.github.ivan.kopylove.kotlin.sandbox.e0364856fbe14f7ebf7ae55d4f2b0195

import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

 class SomeClass

private class MyCustomClass {
    val lazyValue: SomeClass by lazy {

        SomeClass()
    }
}

class LazyInitTest {
    @Test
    fun `should return the same link`() {
        // given
        val instance = MyCustomClass()
        val link = instance.lazyValue
        // when


        // then
        assertTrue(link === instance.lazyValue)
        assertTrue(link === instance.lazyValue)
    }
}