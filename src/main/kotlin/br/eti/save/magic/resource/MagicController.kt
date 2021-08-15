package br.eti.save.magic.resource

import br.eti.save.magic.model.CharacterCreateRequest
import br.eti.save.magic.model.CharacterResponse
import br.eti.save.magic.model.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class MagicController() {

    @Autowired
    lateinit var service: CharacterService

    @PostMapping("/")
    fun createCharacter(@RequestBody character: CharacterCreateRequest): ResponseEntity<CharacterResponse> {
        val createdCharacter = service.create(character)
        val uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(createdCharacter.id)
            .toUri()
        return ResponseEntity.created(uri).body(createdCharacter)
    }

    @GetMapping("/{id}")
    fun searchCharacterById(@PathVariable id: String): ResponseEntity<CharacterResponse> {
        val character = service.findById(id)
        return ResponseEntity.ok(character)
    }

}
