package com.github.ivan.kopylove.kotlin.sandbox.b4b0e69ae2214b3b9e15cacc0fb1638d

import java.util.*
import kotlin.math.min


class ATM(private val initialStorage: Map<Nominal, Int>) {

    //лучше хранить
    private var currentStorage: MutableMap<Nominal, Int> = initialStorage.toMutableMap()

    fun widthdraw(sum: Int): List<Nominal>
    {
        val collector = mutableMapOf<Nominal, Int>()
        var result = sum
        currentStorage.forEach {
            val counted = result / it.key.value

            val min = min(counted, it.value)
            collector.putIfAbsent(it.key, min)

            result -= (it.key.value * min)

            if(result == 0) {
                return@forEach
            }
        }

        if(result !=0) {
            throw RuntimeException("not available")
        }
        else {
            collector.forEach {
                collector.computeIfPresent(it.key) { _, value -> value - 1 }
            }
        }




        return collector.map { it.key }


    }
}
/**
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 *
 * Другие валюты и номиналы должны легко добавляться разработчиками в будущем.
 * Многопоточные сценарии могут быть добавлены позже (например резервирование).
 */


class Bill(
    val value: Nominal,
    val currency: Currency
)
enum class Nominal(val value: Int) {
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUNTHAND(1000),
    FIVE_THOUSAND(5000)
}