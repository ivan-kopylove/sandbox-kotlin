package com.github.ivan.kopylove.kotlin.sandbox.d130342e83b64398a7eae19f4d9b7cfb.step01

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
/**
 * https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver
 */
class ReceiverPlaygroundHtml {

    @Test
    fun `should format the code`() {
        // given
        val html = html {       // lambda with receiver begins here
            text("abc")   // calling a method on the receiver object
        }

        // then
        fail("try to check yourself before running the assertion")
        assertThat(html.myVal, equalTo("abc"))
    }

    class HTML {
        lateinit var myVal: String
        fun text(text: String) {
            myVal = text
        }
    }

    // this is slighly modified factory method, it just builds an object with modifier
    fun html(init: HTML.() -> Unit): HTML {
        val html = HTML()  // create the receiver object
        html.init()   // pass the receiver object to the lambda
        return html
    }

}