package com.oneotrixwork.sportcrm

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "hi ${platform.name}"
    }
}