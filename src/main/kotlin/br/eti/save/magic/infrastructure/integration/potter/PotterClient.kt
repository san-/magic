package br.eti.save.magic.infrastructure.integration.potter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PotterClient (@Autowired private val potterFeignClient: PotterFeignClient) {

    fun getHouses(): Collection<House> {
        return potterFeignClient.getHouses().orElseThrow().houses
    }

}
