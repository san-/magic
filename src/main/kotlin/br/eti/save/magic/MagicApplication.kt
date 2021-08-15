package br.eti.save.magic

import ch.qos.logback.classic.BasicConfigurator
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
class MagicApplication

fun main(args: Array<String>) {
	runApplication<MagicApplication>(*args)
}
