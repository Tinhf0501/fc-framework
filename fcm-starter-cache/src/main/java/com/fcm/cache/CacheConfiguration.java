package com.fcm.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfiguration {

    @Autowired
    private CacheProperties cacheProperties;

    /**
     * cache level 1
     * @return
     */
    @Bean(CacheConstant.DEFAULT_CACHE_MANAGER)
    public CacheManager defaultCacheManager() {
        List<Cache> caches = new ArrayList<>();
        this.cacheProperties.getCache().forEach((key, value) -> {
            Cache c = new CaffeineCache(key, Caffeine.newBuilder()
                    .expireAfterWrite(value.getTtl())
                    .ticker(Ticker.systemTicker())
                    .build());
            caches.add(c);
        });
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }


    /**
     * cache level 2: redis
     */
    @Bean(CacheConstant.REDIS_CACHE_MANAGER)
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {

        final RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

        Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();
        this.cacheProperties.getCache().forEach((key, value) -> {
            cacheConfigurationMap.put(key, RedisCacheConfiguration
                    .defaultCacheConfig()
                    .disableCachingNullValues()
                    .entryTtl(value.getTtl())
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
            );
        });

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(cacheConfigurationMap)
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
