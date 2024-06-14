package com.github.ivan.kopylove.kotlin.sandbox.acb19cb3740c48e3adf198155673cf1b

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.util.Collections.synchronizedMap

/**
 * koan
 */
class MapDeclarationTest {
    @Test
    fun `should throw`() {
        // given
        val extras: MutableMap<String, String> = synchronizedMap(mapOf("initial value" to "1"))

        // when
        assertThrows<UnsupportedOperationException> {
            extras["value added"] = "some value"
        }
    }

    @Test
    fun `should accept value`() {
        // given
        val extras: MutableMap<String, String> = synchronizedMap(mutableMapOf("initial value" to "1"))

        // when
        assertDoesNotThrow {
            extras["value added"] = "some value"
        }
    }
}