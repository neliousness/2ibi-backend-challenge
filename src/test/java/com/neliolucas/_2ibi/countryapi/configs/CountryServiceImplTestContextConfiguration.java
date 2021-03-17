package com.neliolucas._2ibi.countryapi.configs;

import com.neliolucas._2ibi.countryapi.services.CountryService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
public class CountryServiceImplTestContextConfiguration {

    @Bean
    public CountryService countryService()
    {
        return new CountryService() {

        };
    }
}
