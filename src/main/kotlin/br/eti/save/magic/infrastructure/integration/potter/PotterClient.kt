package br.eti.save.magic.infrastructure.integration.potter

import br.eti.save.magic.application.exception.ThirdPartException
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator

@Service
@Slf4j
@CacheConfig(cacheNames = ["houses"])
@EnableScheduling
class PotterClient(@Autowired private val potterFeignClient: PotterFeignClient) {

    private val logger: Logger = LoggerFactory.getLogger(PotterClient::class.java)

    @Retryable(value = [RuntimeException::class], maxAttempts = 3, backoff = Backoff(delay = 10))
    @Cacheable
    fun getHouses(): Collection<House> {
        logger.info("${this.javaClass.name}.getHouses: trying to get houses list")
        return potterFeignClient.getHouses().orElseThrow().houses
    }

    @Recover
    fun getHouses(ex: RuntimeException): Collection<House> {
        throw  ThirdPartException(
            "Recover getHouses: its not possible to obtain the houses list. [${ex.javaClass.name}: ${ex.message}]",
            ex
        )
    }

    @CacheEvict(cacheNames = ["houses"])
    @Scheduled(cron = "0 0 * * * *")
    fun cacheInvalidate(){
        logger.info("House's cache cleared successfully")
    }
}
