package com.github.ivan.kopylove.kotlin.sandbox.d130342e83b64398a7eae19f4d9b7cfb.step02

import com.github.ivan.kopylove.kotlin.sandbox.d130342e83b64398a7eae19f4d9b7cfb.step02.HtmlTableGenerator.Table.Companion.table
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
class HtmlTableGenerator {
    @Test
    fun `should generate table with one subtag`() {
        // given
        

        val table = table {
            tr {
                setVal("my data")
            }
        }

        // when
        val result = table.toString()

        // then
        fail("try to check yourself before running the assertion")
        assertThat(result, equalTo("<table><tr>my data</tr></table>"))
    }

    private open class Tag(val tagName: String) {
        val children = mutableListOf<Tag>()
        private var tagValue: String? = null

        fun setVal(value: String) {
            tagValue = value
        }

        override fun toString(): String {
            return """<$tagName>${if (tagValue != null) tagValue else children.joinToString()}</$tagName>"""
        }
    }

    private class Table : Tag("table") {
        fun tr(init: Tag.() -> Unit) {
            val tr = Tag("tr")
            tr.init()
            children.add(tr)
        }

        companion object {
            fun table(init: Table.() -> Unit): Table {
                val tag = Table()
                tag.init()
                return tag
            }
        }
    }
}