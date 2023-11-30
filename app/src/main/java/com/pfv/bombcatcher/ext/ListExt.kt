package com.pfv.bombcatcher.ext

import kotlin.random.Random

fun <T> List<T>.randomIndex(): Int {
    return if (this.isNotEmpty()) {
        Random.nextInt(this.size)
    } else {
        0
    }
}