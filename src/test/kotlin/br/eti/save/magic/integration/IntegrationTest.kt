package br.eti.save.magic.integration

import br.eti.save.magic.domain.CharacterRequest
import br.eti.save.magic.infrastructure.pesistence.Character
import br.eti.save.magic.infrastructure.pesistence.CharacterRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print as log

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTest {

    companion object {
        const val ROOT_URI = "/magics"
        val character = CharacterRequest(
            name = "Harry Potter",
            role = "student",
            school = "Hogwarts School of Witchcraft and Wizardry",
            house = "1760529f-6d51-4cb1-bcb1-25087fce5bde",
            patronus = "stag"
        )

        val charactersList = listOf(
            Character(
                id = "",
                name = "Harry Potter",
                role = "student",
                school = "Hogwarts School of Witchcraft and Wizardry",
                house = "1760529f-6d51-4cb1-bcb1-25087fce5bde",
                patronus = "stag"
            ),
            Character(
                id = "",
                name = "Hermione Granger",
                role = "student",
                school = "Goderic Gryffindor",
                house = "1760529f-6d51-4cb1-bcb1-25087fce5bde",
                patronus = "otter"
            )
        )
    }

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var repository: CharacterRepository

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @BeforeEach
    fun setupMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
        repository.deleteAll()
    }

    @Test
    fun shouldGetEmptyList() {
        mockMvc.perform(
            get(ROOT_URI)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(log())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.`is`(0)))
    }

    @Test
    fun shouldGet404_whenNotFound() {
        mockMvc.perform(
            get("$ROOT_URI/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(log())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun shouldGet201_whenCreateACharacter_onHappyWay() {
        mockMvc.perform(
            post(ROOT_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(character))
        ).andDo(log())
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.header().exists("Location"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
    }

    @Test
    @Order(4)
    fun shouldGetCharactersList() {
        repository.saveAllAndFlush(charactersList)

        mockMvc.perform(
            get(ROOT_URI)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(log())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.`is`(2)))
    }


}
