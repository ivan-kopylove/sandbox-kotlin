package com.github.ivan.kopylove.kotlin.sandbox.ae8016d99e7d4df0997d351c5047e5f4

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.util.*
import java.util.concurrent.ConcurrentHashMap


class ConcurrentModificationTest {

    @Nested
    inner class ProblemDemonstration {
        @Test
        fun concurrentModificationError() {
            val synchronizedMap = Collections.synchronizedMap(mutableMapOf(1 to "fish", 2 to "hashmap"))

            val iterator: Iterator<Map.Entry<Int, String>> = synchronizedMap.entries.iterator()

            // when
            assertThrows<ConcurrentModificationException> {
                while (iterator.hasNext()) {
                    synchronizedMap[3] = "Modification"
                    iterator.next()
                }
            }
        }
    }

    @Nested
    inner class Solution {
        @Test
        fun concurrentModificationError() {
            val concurrentHashMap = ConcurrentHashMap<Int, String>()
            concurrentHashMap[1] = "fish"
            concurrentHashMap[2] = "hashmap"

            val iterator: Iterator<Map.Entry<Int, String>> = concurrentHashMap.entries.iterator()

            // when
            assertDoesNotThrow {
                while (iterator.hasNext()) {
                    concurrentHashMap[3] = "Modification"
                    iterator.next()
                }
            }
        }
    }


}