package br.eti.save.magic

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
@EnableCaching
class MagicApplication

fun main(args: Array<String>) {
	runApplication<MagicApplication>(*args)
}
