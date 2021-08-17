package br.eti.save.magic.infrastructure.integration.potter

import br.eti.save.magic.application.configuration.PotterFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@FeignClient(name="potter", url="\${potterApi.url}", configuration = [PotterFeignConfig::class])
interface PotterFeignClient {

    @GetMapping(produces = ["application/json"])
    fun getHouses(): Optional<HouseWrapper>

}
