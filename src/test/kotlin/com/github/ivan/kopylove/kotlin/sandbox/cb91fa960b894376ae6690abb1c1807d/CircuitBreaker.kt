package com.github.ivan.kopylove.kotlin.sandbox.cb91fa960b894376ae6690abb1c1807d

import com.github.ivan.kopylove.kotlin.sandbox.cb91fa960b894376ae6690abb1c1807d.CircuitBreakerStatus.CLOSED
import com.github.ivan.kopylove.kotlin.sandbox.cb91fa960b894376ae6690abb1c1807d.CircuitBreakerStatus.HALF_OPENED
import com.github.ivan.kopylove.kotlin.sandbox.cb91fa960b894376ae6690abb1c1807d.CircuitBreakerStatus.OPENED
import java.util.concurrent.Executors.newSingleThreadScheduledExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.SECONDS
import kotlin.math.max

enum class CircuitBreakerStatus {
    OPENED, CLOSED, HALF_OPENED
}



class CircuitBreaker(
    private val window: Int = DEFAULT_NUMBER_OF_REQUESTS_WINDOW,
    private val cooldownSeconds: Long = DEFAULT_COOLDOWN_SECONDS,
) {

    private var status = OPENED
    private val requests = mutableListOf<Boolean>()
    private val halfOpenMover = newSingleThreadScheduledExecutor()
    private var recoveryRequestCheck = EACH_REQUEST_WHEN_RECOVERING
    private var recoveryRequestNumber = INITIAL_RECOVERING_REQUESTS_COUNTER_VALUE

    @Synchronized
    fun isOpen(): Boolean {
        println("circuit breaker status check | current status = $status")

        return when (status) {
            OPENED -> true
            HALF_OPENED -> {
                recoveryRequestNumber++ % recoveryRequestCheck == 0
            }

            CLOSED -> false
        }
    }

    @Synchronized
    fun registerSuccess() {
        when (status) {
            OPENED -> {
                requests.add(SUCCESSFUL_REQUEST)
            }

            HALF_OPENED -> {
                requests.add(SUCCESSFUL_REQUEST)
                val failedSuccessRatio = failedSuccessRatio()
                println("failedSuccessRatio = $failedSuccessRatio")
                if (failedSuccessRatio == RECOVERED_RATIO) {
                    status = OPENED
                    println("circuit breaker update | new status = $status | failedSuccessRatio = $failedSuccessRatio")
                }
            }

            CLOSED -> {}
        }
    }

    @Synchronized
    fun registerFailure() {
        when (status) {
            OPENED, HALF_OPENED -> {
                requests.add(FAILED_REQUEST)
                val failedSuccessRatio = failedSuccessRatio()
                println("circuit breaker update | failedSuccessRatio = $failedSuccessRatio")
                if (failedSuccessRatio > FAILED_RATIO) {
                    status = CLOSED
                    requests.clear()
                    println("circuit breaker update | new status = $status | failedSuccessRatio = $failedSuccessRatio")
                    halfOpenMover.schedule({
                        recoveryRequestNumber = INITIAL_RECOVERING_REQUESTS_COUNTER_VALUE
                        status = HALF_OPENED
                        println("circuit breaker update | new status = $status")
                    }, cooldownSeconds, SECONDS)
                }
            }

            CLOSED -> {}
        }
    }

    private fun failedSuccessRatio(): Float {
        val lastNRequestsWindow = requests.drop(max(requests.size - window, 0))
        if (lastNRequestsWindow.size >= window) {
            val failed = lastNRequestsWindow.filter { !it }.size
            val successful = max(lastNRequestsWindow.filter { it }.size, 1)
            return failed.toFloat() / successful
        }
        return -1f
    }

    companion object  {
        private const val FAILED_REQUEST = false
        private const val SUCCESSFUL_REQUEST = false
        private const val DEFAULT_NUMBER_OF_REQUESTS_WINDOW = 20
        private const val DEFAULT_COOLDOWN_SECONDS = 60L * 20
        private const val EACH_REQUEST_WHEN_RECOVERING = 5
        private const val INITIAL_RECOVERING_REQUESTS_COUNTER_VALUE = 0

        /**
         * percent of requests to consider as the circuit breaker as closed
         */
        private const val FAILED_RATIO = 0.3

        /**
         * percent of requests to consider as the circuit breaker as recovered
         */
        private const val RECOVERED_RATIO = 0f

    }
}