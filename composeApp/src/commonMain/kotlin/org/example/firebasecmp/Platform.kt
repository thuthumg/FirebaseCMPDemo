package org.example.firebasecmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform