package com.example.mmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

interface BestPlatform {
    fun invoke()
}