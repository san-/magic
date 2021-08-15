package br.save.eti.magic.infrastructure.integration.potter

import br.save.eti.magic.configuration.PotterFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@FeignClient(name="potter", url="\${potterApi.url}", configuration = [PotterFeignConfig::class])
interface PotterClient {

    @GetMapping(produces = ["application/json"])
    fun getHouses(): Optional<HouseWrapper>

}
