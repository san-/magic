package br.save.eti.magic.configuration

import feign.Logger
import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean


class PotterFeignConfig {

    @Value(value =  "\${potterApi.apiKey}")
    lateinit var apiKey: String;

    @Bean
    fun feignLoggerLevel(): Logger.Level{
        return Logger.Level.FULL
    }

    @Bean
    fun requestTokenBearerInterceptor(): RequestInterceptor? {
        return RequestInterceptor {
            it.header("apikey", apiKey)
        }
    }

}
