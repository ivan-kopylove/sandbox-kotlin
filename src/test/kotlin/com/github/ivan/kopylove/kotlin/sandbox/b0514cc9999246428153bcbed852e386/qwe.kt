package com.github.ivan.kopylove.kotlin.sandbox.b0514cc9999246428153bcbed852e386

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class Set {
    @Test
    fun `test should be renamed once`() {
        // given
        val mutableListOf = mutableListOf(true)
        (1..100_000_000).forEach {
            mutableListOf.add(true)
        }

        fail("try to check yourself before running the assertion")
        assertThat(mutableListOf.size, equalTo(mutableListOf.toSet().size)) // true or false?
        
    }
}