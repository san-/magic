package br.eti.save.magic.infrastructure.pesistence

import org.apache.logging.log4j.util.Strings
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@ExtendWith(MockitoExtension::class)
@DataJpaTest
class CharacterTest() {

    @Autowired
    lateinit var characterRepository: CharacterRepository

@Test
fun shouldRetrieveSavedCharacter(){
    val character = Character(Strings.EMPTY,  "TesteNome","student", "Hogwarts", "1234", "stag" )
    characterRepository.saveAndFlush(character)
    val characterFromDB = characterRepository.findById(character.id).orElseThrow()
    assertTrue(character.name == characterFromDB.name)
}

}
