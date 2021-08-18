package br.eti.save.magic.infrastructure.integration.potter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.retry.support.RetryTemplate
import java.util.*


@ExtendWith(MockitoExtension::class)
internal class PotterClientTest {

    @InjectMocks
    private lateinit var potterClient: PotterClient

    @Mock
    private lateinit var potterFeignClient: PotterFeignClient

    @Autowired
    private lateinit var retryTemplate: RetryTemplate

    @Test
    fun shouldGetHouses() {
        val expectedHouses = listOf(House("1", "nome1"), House("2", "nome2"))
        `when`(potterFeignClient.getHouses()).thenReturn(Optional.of(HouseWrapper(expectedHouses)))
        val houses = potterClient.getHouses()
        assertThat(houses).asList().hasSize(2)
        houses.forEach {
            assertThat(it.id).isInstanceOf(String::class.java).isBetween("1", "2")
        }
    }

}
