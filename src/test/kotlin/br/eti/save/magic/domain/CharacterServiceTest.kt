package br.eti.save.magic.domain

import br.eti.save.magic.application.exception.CharacterNotFoundException
import br.eti.save.magic.application.exception.HouseNotFoundException
import br.eti.save.magic.infrastructure.integration.potter.House
import br.eti.save.magic.infrastructure.integration.potter.PotterClient
import br.eti.save.magic.infrastructure.pesistence.Character
import br.eti.save.magic.infrastructure.pesistence.CharacterRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.Mockito.anyString
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CharacterServiceTest() {

    @InjectMocks
    lateinit var service: CharacterService

    @Mock
    lateinit var potterClient: PotterClient

    @Mock
    lateinit var characterRepository: CharacterRepository

    @Test
    fun shouldCreateCharacter() {
        val expectedHouseId = "valid-house"
        val expectedHouseName = "House"
        val expectedName = "Teste"
        val expectedId = "1"
        `when`(potterClient.getHouses()).thenReturn(
            Collections.singletonList(
                House(
                    id = expectedHouseId,
                    expectedHouseName
                )
            )
        )
        `when`(characterRepository.save(any(Character::class.java))).thenReturn(
            Character(
                id = expectedId,
                name = expectedName,
                house = expectedHouseId,
                patronus = "",
                role = "",
                school = ""
            )
        )
        val character = service.create(CharacterRequest(name = expectedName, house = expectedHouseId))
        assertThat(character.house).isEqualTo(expectedHouseId)
        assertThat(character.id).isEqualTo(expectedId)
        assertThat(character.name).isEqualTo(expectedName)
        verify(characterRepository, atLeastOnce()).save(any())
    }

    @Test
    fun shouldNotCreateCharacter_whenHouseIdIsNotValid() {
        val expectedValidHouseId = "valid-house"
        val expectedInvalidHouseId = "invalid-house"
        val expectedHouseName = "House"
        val expectedName = "Teste"
        `when`(potterClient.getHouses()).thenReturn(
            Collections.singletonList(
                House(
                    id = expectedValidHouseId,
                    expectedHouseName
                )
            )
        )
        assertThatThrownBy {
            service.create(
                CharacterRequest(
                    name = expectedName,
                    house = expectedInvalidHouseId
                )
            )
        }.isInstanceOf(HouseNotFoundException::class.java)
    }

    @Test
    fun shouldFindById() {
        val expectedId = "1"
        `when`(characterRepository.findById(anyString())).thenReturn(
            Optional.of(
                Character(
                    id = expectedId,
                    name = "",
                    house = "",
                    patronus = "",
                    role = "",
                    school = ""
                )
            )
        )
        val character = service.findById(expectedId)
        assertThat(character).isNotNull
        assertThat(character.id).isEqualTo(expectedId)
    }

    @Test
    fun shouldThrow_whenNotFoundById() {
        `when`(characterRepository.findById(anyString())).thenReturn(Optional.empty())
        assertThatThrownBy { service.findById("99") }.isInstanceOf(CharacterNotFoundException::class.java)
    }

    @Test
    fun shouldDeleteById() {
        `when`(characterRepository.existsById(anyString())).thenReturn(true)
        assertThatCode { service.deleteById("99") }.doesNotThrowAnyException()
        verify(characterRepository, atLeastOnce()).deleteById(anyString())
    }

    @Test
    fun shouldThrow_whenNotFoundForDelete() {
        `when`(characterRepository.existsById(anyString())).thenReturn(false)
        assertThatThrownBy { service.deleteById("99") }.isInstanceOf(CharacterNotFoundException::class.java)
            .hasMessageContaining("not found for delete")
    }

    @Test
    fun shouldUpdateById() {
        `when`(characterRepository.existsById(anyString())).thenReturn(true)
        `when`(characterRepository.save(any(Character::class.java))).thenReturn(
            Character("", "", "", "", "", "")
        )
        assertThatCode { service.update("99", CharacterRequest()) }.doesNotThrowAnyException()
        verify(characterRepository, atLeastOnce()).save(any(Character::class.java))
    }

    @Test
    fun shouldThrow_whenNotFoundForUpdate() {
        `when`(characterRepository.existsById(anyString())).thenReturn(false)
        assertThatThrownBy {
            service.update(
                "99",
                CharacterRequest()
            )
        }.isInstanceOf(CharacterNotFoundException::class.java)
            .hasMessageContaining("not found for update")
    }

}
