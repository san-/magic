package br.eti.save.magic.infrastructure.integration.potter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
internal class PotterFeignClientTest {

    @Autowired
    private lateinit var potterFeignClient: PotterFeignClient

	@Test
	fun shouldGetHouses() {

        val optionalHouses = potterFeignClient.getHouses()
        assertThat(optionalHouses).isInstanceOf(Optional::class.java)

        val houses = optionalHouses.orElseThrow()
        assertThat(houses).isInstanceOf(HouseWrapper::class.java)

        houses.houses.forEach {
            assertThat(it.id).isInstanceOf(String::class.java)
            assertThat(it.name).isInstanceOf(String::class.java)
        }

	}

}
