package com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v2.usecase

import com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v2.api.MyNew_Api

class MyNew_UseCase(
    private val one: IllegalArgumentException, private val two: IllegalArgumentException
) : MyNew_Api {

    override fun renameMe(payload: MyNew_Api.Payload): MyNew_Api.Result {
        return MyNew_Api.Result(java.lang.IllegalArgumentException())
    }
}