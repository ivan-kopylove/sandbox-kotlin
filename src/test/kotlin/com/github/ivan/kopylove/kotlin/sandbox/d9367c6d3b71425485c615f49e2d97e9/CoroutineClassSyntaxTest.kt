package com.github.ivan.kopylove.kotlin.sandbox.d9367c6d3b71425485c615f49e2d97e9

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test

class CoroutineClassSyntaxTest {
    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun `should demonstrate coroutine inside a separate class`() {
        // when
        (1..100_000).forEach {
            GlobalScope.launch(block = CoroutineFactory.buildCoroutine())
        }
        Thread.sleep(2000)
    }
}