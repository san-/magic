package br.eti.save.magic.infrastructure.cache

import br.eti.save.magic.infrastructure.integration.potter.PotterClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Component

@Component
class InternalCache {

    private val logger: Logger = LoggerFactory.getLogger(PotterClient::class.java)

    @Autowired
    private lateinit var cacheManager: CacheManager

    fun invalidateAllCaches() {
        val cacheNames = cacheManager.cacheNames
        cacheNames.forEach { cacheManager.getCache(it)?.clear() }
        logger.info("All caches cleared")
    }

    fun invalidateCache(name: String) {
        cacheManager.getCache(name)?.clear()
    }

    fun invalidateCache(name: String, key: String) {
        cacheManager.getCache(name)?.evictIfPresent(key)
    }


}
