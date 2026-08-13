package com.log4om.api.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableConfigurationProperties(Log4omProperties::class)
class AppConfig(
    private val props: Log4omProperties
) {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer = object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            val origins = props.cors.allowedOrigins.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .toTypedArray()
            registry.addMapping("/**")
                .allowedOrigins(*origins)
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600)
        }
    }
}
