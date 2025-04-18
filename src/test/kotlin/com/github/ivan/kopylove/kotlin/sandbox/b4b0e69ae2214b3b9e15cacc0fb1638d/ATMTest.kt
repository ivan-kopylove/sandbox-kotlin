package com.github.ivan.kopylove.kotlin.sandbox.b4b0e69ae2214b3b9e15cacc0fb1638d

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ATMTest {
        @Test
        fun test1() {
            // given
            val initialStorage: Map<Nominal, Int> = mapOf(
                Nominal.FIVE_THOUSAND to 2,
                Nominal.FIVE_HUNDRED to 1)

            val sut = ATM(initialStorage)

            // when
            assertThrows<RuntimeException> {
                sut.widthdraw(10_200)
            }
        }

        @Test
        fun test2() {
            // given
            val initialStorage: Map<Nominal, Int> = mapOf(
                Nominal.FIVE_THOUSAND to 2,
                Nominal.FIVE_HUNDRED to 1)

            val sut = ATM(initialStorage)

            // when
            sut.widthdraw(10_500)

        }
}