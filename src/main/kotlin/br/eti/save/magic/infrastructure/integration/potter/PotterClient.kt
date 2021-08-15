package br.eti.save.magic.infrastructure.integration.potter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PotterClient (@Autowired private val potterFeignClient: PotterFeignClient) {

    fun getHouses(): Optional<HouseWrapper> {
        return potterFeignClient.getHouses()
    }

}
