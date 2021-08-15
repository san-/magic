package br.save.eti.magic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MagicApplication

fun main(args: Array<String>) {
	runApplication<MagicApplication>(*args)
}
