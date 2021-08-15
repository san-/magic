package br.eti.save.magic.infrastructure.integration.potter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class PotterClientTest {

    @InjectMocks
    lateinit var potterClient: PotterClient

    @Mock
    lateinit var potterFeignClient: PotterFeignClient

    @Test
    fun shouldGetHouses(){
        val expectedHouses = listOf(House("1", "nome1"), House("2", "nome2"))
        Mockito.`when`(potterFeignClient.getHouses()).thenReturn(Optional.of(HouseWrapper(expectedHouses)))
        val response = potterClient.getHouses()
        assertThat(response).isInstanceOf(Optional::class.java).isNotEmpty
        val houses = response.orElseThrow().houses
        assertThat(houses).asList().hasSize(2)
        houses.forEach{
            assertThat(it.id).isInstanceOf(String::class.java).isBetween("1", "2")
        }
    }

}
