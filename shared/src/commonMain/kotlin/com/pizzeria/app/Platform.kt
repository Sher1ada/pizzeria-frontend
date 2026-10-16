package com.pizzeria.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform