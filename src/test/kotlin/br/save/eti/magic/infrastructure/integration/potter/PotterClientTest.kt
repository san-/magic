package br.save.eti.magic.infrastructure.integration.potter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class PotterClientTest(
    @Autowired
    private val potterClient: PotterClient
) {

	@Test
	fun shouldGetHouses() {

        val houses = potterClient.getHouses()
        assertThat(houses).isInstanceOf(HouseWrapper::class.java)


	}

}
