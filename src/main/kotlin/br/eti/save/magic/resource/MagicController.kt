package br.eti.save.magic.resource

import br.eti.save.magic.infrastructure.integration.potter.HouseWrapper
import br.eti.save.magic.infrastructure.integration.potter.PotterClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MagicController(@Autowired
                      private val potterClient: PotterClient
) {
    @GetMapping("/", produces = ["application/json"])
    fun index(): ResponseEntity<HouseWrapper> {
        val houses = potterClient.getHouses()
        return ResponseEntity.ok(houses.get())
    }

}
