package com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v1.adapter

import com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v1.spi.MyNewSpi
import com.github.ivan.kopylove.kotlin.sandbox.aeb92aa36ef64975a02614c4a37d9b6a.v1.api.MyNewApi

class MyNewAdapter(
    private val myNewApi: MyNewApi
) : MyNewSpi {
    override fun renameMe(payload: MyNewSpi.Payload): MyNewSpi.Result {
        return MyNewSpi.Result(
            myNewApi.renameMe(
                MyNewApi.Payload(
                    payload.putMyArgumentHere
                )
            ).putMyArgumentHere
        )
    }
}