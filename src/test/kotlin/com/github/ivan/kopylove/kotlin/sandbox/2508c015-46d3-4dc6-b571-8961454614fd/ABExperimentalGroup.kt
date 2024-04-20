package com.github.ivan.kopylove.kotlin.sandbox.dsajdsklad


enum class ABExperimentalGroup {
    /**
     * Control group.
     */
    A,

    /**
     * Experimental group.
     */
    B,

    /**
     * Before splitting into Control group / Experimental group.
     */
    TECHNICAL_STEP,
    ;

    companion object {

        fun <T> runABSplit(a: () -> T, b: () -> T): T {
//            val group = getABGroup()
//
//            logger.trace("running $script callback")
            return when ("group") {
                "A" -> a()
                "B" -> b()
                "TECHNICAL_STEP" -> throw IllegalArgumentException("$TECHNICAL_STEP is not supported at this point")
                else -> throw RuntimeException("")
            }
        }
    }
}