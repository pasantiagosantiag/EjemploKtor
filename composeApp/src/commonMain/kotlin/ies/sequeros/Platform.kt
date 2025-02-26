package ies.sequeros

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform