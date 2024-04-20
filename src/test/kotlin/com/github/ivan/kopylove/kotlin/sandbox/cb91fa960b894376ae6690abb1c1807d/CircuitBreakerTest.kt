package com.github.ivan.kopylove.kotlin.sandbox.cb91fa960b894376ae6690abb1c1807d

import org.awaitility.kotlin.await
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.concurrent.TimeUnit.SECONDS
import kotlin.random.Random

class CircuitBreakerTest {

    @Nested
    inner class Transitions {
        @Test
        fun `open to closed - should be closed on reaching failed threshold`() {
            // given
            val window = Random.nextInt(5, 1000)
            val breaker = CircuitBreaker(window = window)

            // when
            triggerClosing(window, breaker)

            // then
            (0..EXCESSIVE_NUMBER_OF_ATTEMPTS).forEach { _ ->
                breakerIsClosed(breaker)
            }
        }

        @Test
        fun `half open to closed - should be closed again if recovery failed`() {
            // given
            val window = Random.nextInt(5, 1000)
            val cooldownSeconds = Random.nextLong(3, 8)
            val breaker = CircuitBreaker(
                window = window,
                cooldownSeconds = cooldownSeconds,
            )

            triggerClosing(window, breaker)

            // given breaker is closed
            breakerIsClosed(breaker)

            waitForCooldownExpired(cooldownSeconds, breaker)

            // given breaker is moved to closed state
            triggerClosing(window, breaker)

            (0..EXCESSIVE_NUMBER_OF_ATTEMPTS).forEach { _ ->
                breakerIsClosed(breaker)
            }
        }
    }

    @Nested
    inner class BeingInState {

        @Test
        fun `opened - should be in opened state before minimal window is reached`() {
            // given
            val window = Random.nextInt(5, 1000)
            val breaker = CircuitBreaker(window = window)

            // when
            (1..window).forEach { _ ->
                breakerIsOpened(breaker)
                breaker.registerFailure()
            }
        }

        @Test
        fun `opened - should operate normally for successful calls`() {
            // given
            val breaker = CircuitBreaker()

            // when
            (1..EXCESSIVE_NUMBER_OF_ATTEMPTS).forEach { _ ->
                breakerIsOpened(breaker)
                breaker.registerSuccess()
            }
        }

        @Test
        fun `closed - should ignore recovering requests during a cooldown in closed state`() {
            // given
            val window = Random.nextInt(5, 1000)
            val cooldownSeconds = Random.nextLong(3, 8)

            val breaker = CircuitBreaker(
                window = window,
                cooldownSeconds = cooldownSeconds,
            )

            triggerClosing(window, breaker)

            breakerIsClosed(breaker)

            (0..EXCESSIVE_NUMBER_OF_ATTEMPTS).forEach { _ ->
                breakerIsClosed(breaker)
                breaker.registerSuccess()
            }
        }

        @Test
        fun `half-opened - should accept recovering requests partially after a cooldown`() {
            // given
            val window = Random.nextInt(5, 1000)
            val cooldownSeconds = Random.nextLong(3, 8)

            val breaker = CircuitBreaker(
                window = window,
                cooldownSeconds = cooldownSeconds,
            )

            triggerClosing(window, breaker)
            breakerIsClosed(breaker)
            waitForCooldownExpired(cooldownSeconds, breaker)

            (0..EXCESSIVE_NUMBER_OF_ATTEMPTS).forEach { _ ->
                breakerIsClosed(breaker)
                breakerIsClosed(breaker)
                breakerIsClosed(breaker)
                breakerIsClosed(breaker)
                breakerIsOpened(breaker)
            }
        }
    }

    companion object {
        private const val EXCESSIVE_NUMBER_OF_ATTEMPTS = 9999

        private fun breakerIsOpened(breaker: CircuitBreaker) {
            assertTrue(breaker.isOpen())
        }

        private fun breakerIsClosed(breaker: CircuitBreaker) {
            assertFalse(breaker.isOpen())
        }

        private fun triggerClosing(failedRequests: Int, breaker: CircuitBreaker) {
            (1..failedRequests).forEach { _ ->
                breaker.registerFailure()
            }
        }

        private fun waitForCooldownExpired(cooldownSeconds: Long, breaker: CircuitBreaker) {
            await
                .await()
                .atMost(cooldownSeconds + 1, SECONDS)
                .pollInterval(Duration.ofSeconds(1))
                .until { breaker.isOpen() }
        }
    }
}