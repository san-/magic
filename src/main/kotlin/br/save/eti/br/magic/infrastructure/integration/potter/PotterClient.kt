package br.save.eti.br.magic.infrastructure.integration.potter

import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@FeignClient("potter", path = "\${potterApi.url}")
interface PotterClient {

    @RequestMapping
    @Headers(*["apikey", "\${potterApi.apiKey}"])
    fun getHouses(): Optional<Collection<House>>

}
