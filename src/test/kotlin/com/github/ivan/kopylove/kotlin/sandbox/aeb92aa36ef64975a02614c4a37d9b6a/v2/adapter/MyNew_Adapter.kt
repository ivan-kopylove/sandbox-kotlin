package com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v2.adapter

import com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v2.api.MyNew_Api
import com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v2.spi.MyNew_Spi

class MyNew_Adapter(
    private val myNewApi: MyNew_Api
) : MyNew_Spi {
    override fun renameMe(payload: MyNew_Spi.Payload): MyNew_Spi.Result {
        return MyNew_Spi.Result(
            myNewApi.renameMe(
                MyNew_Api.Payload(
                    payload.myPayload
                )
            ).myResult
        )
    }
}