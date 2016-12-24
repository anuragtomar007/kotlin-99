package org.kotlin99.common

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import java.util.*

fun <T> List<T>.tail(): List<T> = drop(1)

fun <T> Array<out T>.tail(): List<T> = drop(1)

fun <T> Iterable<T>.tail(): List<T> = drop(1)

fun <T> Iterable<T>.toSeq(): Sequence<T> {
    val iterator = this.iterator()
    return object: Sequence<T> {
        override fun iterator(): Iterator<T> {
            return iterator
        }
    }
}

fun <T> ArrayList<T>.fill(n: Int, value: T): ArrayList<T> {
    1.rangeTo(n).forEach { add(value) }
    return this
}

fun <E> List<List<E>>.transpose(): List<List<E>> {
    if (isEmpty()) return this

    val width = first().size
    if (any{ it.size != width }) {
        throw IllegalArgumentException("All nested lists must have the same size, but sizes were ${map{ it.size }}")
    }

    return (0 until width).map { col ->
        (0 until size).map { row -> this[row][col] }
    }
}


class CollectionsTest {
    @Test fun `transpose lists`() {
        assertThat(emptyList<List<Int>>().transpose(), equalTo(emptyList<List<Int>>()))
        assertThat(listOf(emptyList<Int>()).transpose(), equalTo(emptyList<List<Int>>()))

        assertThat(listOf(listOf(1)).transpose(), equalTo(listOf(listOf(1))))

        assertThat(listOf(listOf(1, 2, 3)).transpose(), equalTo(listOf(listOf(1), listOf(2), listOf(3))))

        assertThat(listOf(listOf(1, 2, 3), listOf(4, 5, 6)).transpose(), equalTo(listOf(
                listOf(1, 4), listOf(2, 5), listOf(3, 6)
        )))
    }
}