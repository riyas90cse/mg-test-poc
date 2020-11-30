package com.mg.api.poc.service;

import com.mg.api.poc.model.JsonData;
import com.mg.api.poc.util.AppUtil;
import lombok.extern.log4j.Log4j2;
import org.ehcache.CacheManager;
import org.ehcache.core.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Json Data Service
 * @author Mohamed Riyas
 */
@Service
@Log4j2
public class JsonDataService implements IJsonData {

    private static final String URL = "https://mgtechtest.blob.core.windows.net/files/showcase.json";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Load Data Method
     * To load the data from the HttpClient URL
     * @return String from the URL by resttempalte
     */
    @Cacheable(value = "jsonData")
    @Override
    public String loadData() {
        LOG.info("Retrieving Data from HTTP URL");
        return this.restTemplate.getForObject(URL, String.class);
    }

    /**
     * Clear Cache Method
     */
    @CacheEvict(value = "jsonData", allEntries = true)
    @Override
    public void clearCache() {
        LOG.info("Cleared Data cache");
    }

}
