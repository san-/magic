package br.eti.save.magic.domain

import br.eti.save.magic.application.exception.CharacterNotFoundException
import br.eti.save.magic.application.exception.HouseNotFoundException
import br.eti.save.magic.application.util.MapperUtil.Mapper
import br.eti.save.magic.infrastructure.cache.InternalCache
import br.eti.save.magic.infrastructure.integration.potter.PotterClient
import br.eti.save.magic.infrastructure.pesistence.Character
import br.eti.save.magic.infrastructure.pesistence.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CharacterService {
    companion object {
        const val CACHE_LIST: String = "characters"
        const val CACHE_ONE = "character"
    }

    @Autowired
    private lateinit var characterRepository: CharacterRepository

    @Autowired
    private lateinit var potterClient: PotterClient

    @Autowired
    private lateinit var cache: InternalCache

    fun create(request: CharacterRequest): CharacterResponse {
        if (potterClient.getHouses().none { it.id == request.house })
            throw HouseNotFoundException()
        var character: Character = Mapper.convert(request)
        character = characterRepository.save(character)
        cache.invalidateCache(CACHE_LIST)
        return Mapper.convert(character)
    }

    @Cacheable(cacheNames = [CACHE_LIST], key = "#page")
    fun findAll(page: Pageable): Page<CharacterResponse> {
        cache.invalidateCache(CACHE_LIST)
        return characterRepository.findAll(page).map { Mapper.convert(it) }
    }

    @Cacheable(cacheNames = [CACHE_ONE], key = "#id")
    fun findById(id: String): CharacterResponse {
        val character = characterRepository.findById(id).orElseThrow { CharacterNotFoundException() }
        return Mapper.convert(character)
    }

    fun deleteById(id: String) {
        if (!characterRepository.existsById(id))
            throw CharacterNotFoundException("Character not found for delete")
        cache.invalidateCache(CACHE_ONE, id)
        cache.invalidateCache(CACHE_LIST)
        characterRepository.deleteById(id)
    }

    fun update(id: String, request: CharacterRequest): CharacterResponse {
        if (!characterRepository.existsById(id))
            throw CharacterNotFoundException("Character not found for update")
        val merged = Character(id, request.name, request.role, request.school, request.house, request.patronus)
        cache.invalidateCache(CACHE_ONE, id)
        cache.invalidateCache(CACHE_LIST)
        return Mapper.convert(characterRepository.save(merged))
    }

    fun housesCacheInvalidate() {
        potterClient.cacheInvalidate()
    }

}
