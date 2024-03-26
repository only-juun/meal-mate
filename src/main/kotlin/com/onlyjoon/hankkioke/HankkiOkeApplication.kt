package com.onlyjoon.hankkioke

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class HankkiOkeApplication

fun main(args: Array<String>) {
    runApplication<HankkiOkeApplication>(*args)
}
