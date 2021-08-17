package br.eti.save.magic.resource

import br.eti.save.magic.domain.CharacterRequest
import br.eti.save.magic.domain.CharacterResponse
import br.eti.save.magic.domain.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
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
    fun createCharacter(@RequestBody character: CharacterRequest): ResponseEntity<CharacterResponse> {
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

    @DeleteMapping("/{id}")
    fun deleteCharacterById(@PathVariable id: String): ResponseEntity<Void> {
        service.deleteById(id)
        return ResponseEntity.accepted().build()
    }

    @PatchMapping("/{id}")
    fun updateCharacter(@PathVariable id: String, @RequestBody character: CharacterRequest): ResponseEntity<CharacterResponse>{
        val response = service.update(id, character)
        return ResponseEntity.ok(response)
    }
}
