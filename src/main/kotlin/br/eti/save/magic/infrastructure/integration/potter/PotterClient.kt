package br.eti.save.magic.infrastructure.integration.potter

import br.eti.save.magic.application.exception.ThirdPartException
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Service
@Slf4j
class PotterClient (@Autowired private val potterFeignClient: PotterFeignClient) {

    private val logger: Logger = LoggerFactory.getLogger(PotterClient::class.java)

    @Retryable(value = [RuntimeException::class], maxAttempts = 3, backoff = Backoff(delay = 10))
    fun getHouses(): Collection<House> {
        logger.info("${this.javaClass.name}.getHouses: trying to get houses list")
        return potterFeignClient.getHouses().orElseThrow().houses
    }

    @Recover
    fun getHouses(ex: RuntimeException): Collection<House>{
        logger.warn("${this.javaClass.name}.getHouses: [${ex.javaClass.name}] ${ex.message}")
        throw ex.message?.let { ThirdPartException(it, ex) }!!
    }

}
