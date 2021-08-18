package br.eti.save.magic.domain

import br.eti.save.magic.application.exception.CharacterNotFoundException
import br.eti.save.magic.application.exception.HouseNotFoundException
import br.eti.save.magic.application.util.MapperUtil.Mapper
import br.eti.save.magic.infrastructure.integration.potter.PotterClient
import br.eti.save.magic.infrastructure.pesistence.Character
import br.eti.save.magic.infrastructure.pesistence.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CharacterService {

    @Autowired
    lateinit var characterRepository: CharacterRepository

    @Autowired
    lateinit var potterClient: PotterClient

    fun create(request: CharacterRequest): CharacterResponse {
        if (potterClient.getHouses().none { it.id == request.house })
            throw HouseNotFoundException()
        var character: Character = Mapper.convert(request)
        character = characterRepository.save(character)
        return Mapper.convert(character)
    }

    fun findById(id: String): CharacterResponse {
        val character = characterRepository.findById(id).orElseThrow { CharacterNotFoundException() }
        return Mapper.convert(character)
    }

    fun deleteById(id: String) {
        if (!characterRepository.existsById(id))
            throw CharacterNotFoundException("Character not found for delete")
        characterRepository.deleteById(id)
    }

    fun update(id: String, request: CharacterRequest): CharacterResponse {
        if (!characterRepository.existsById(id))
            throw CharacterNotFoundException("Character not found for update")
        val merged = Character(id, request.name, request.role, request.school, request.house, request.patronus)
        return Mapper.convert(characterRepository.save(merged))
    }

    fun housesCacheInvalidate() {
        potterClient.cacheInvalidate()
    }

}
