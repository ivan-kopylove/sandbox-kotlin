package com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v1.spi

fun interface MyNewSpi {
        fun renameMe(payload: Payload): Result

            data class Payload(
                val putMyArgumentHere: IllegalArgumentException
            )

            data class Result(
                val putMyArgumentHere: IllegalArgumentException
            )
}