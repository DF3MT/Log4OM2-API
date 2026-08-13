package com.log4om.api.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "log4om")
data class Log4omProperties(
    val jwt: Jwt = Jwt(),
    val encryption: Encryption = Encryption(),
    val cors: Cors = Cors(),
    val tenant: Tenant = Tenant()
) {
    data class Jwt(
        val secret: String = "change-me",
        val accessTokenMinutes: Long = 15,
        val refreshTokenDays: Long = 7
    )

    data class Encryption(
        val key: String = "0123456789abcdef0123456789abcdef"
    )

    data class Cors(
        val allowedOrigins: String = "http://localhost:3000"
    )

    data class Tenant(
        val poolMaxSize: Int = 5,
        val poolIdleTimeoutMs: Long = 600_000,
        val connectionTimeoutMs: Long = 5_000
    )
}
