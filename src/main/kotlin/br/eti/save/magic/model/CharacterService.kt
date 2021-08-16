package br.eti.save.magic.model

import br.eti.save.magic.configuration.CharacterNotFoundException
import br.eti.save.magic.configuration.HouseNotFoundException
import br.eti.save.magic.infrastructure.integration.potter.PotterClient
import br.eti.save.magic.infrastructure.pesistence.Character
import br.eti.save.magic.infrastructure.pesistence.CharacterRepository
import org.apache.logging.log4j.util.Strings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CharacterService {

    @Autowired
    lateinit var characterRepository: CharacterRepository

    @Autowired
    lateinit var potterClient: PotterClient

    fun create(request: CharacterCreateRequest): CharacterResponse {
        if (potterClient.getHouses().none { it.id == request.house })
            throw HouseNotFoundException()

        val character = Character(Strings.EMPTY, request.name, request.role, request.school, request.house, request.patronus)
        val saved = characterRepository.save(character)
        return CharacterResponse(saved.id, saved.name, saved.role, saved.school, saved.house, saved.patronus)
    }

    fun findById(id: String): CharacterResponse {
        val character = characterRepository.findById(id).orElseThrow{CharacterNotFoundException()}
        return CharacterResponse(character.id, character.name, character.role, character.school, character.house, character.patronus)
    }

}
