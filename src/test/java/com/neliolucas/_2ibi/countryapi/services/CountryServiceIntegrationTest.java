package com.neliolucas._2ibi.countryapi.services;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.repositories.CountryRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class CountryServiceIntegrationTest {
//
//    @TestConfiguration
//    public static class CountryServiceImplTestContextConfiguration {
//
//        @Bean
//        public CountryService countryService()
//        {
//            return new CountryService() {
//
//            };
//        }
//
//
//    }
//
//    @Autowired
//    CountryService countryService;
//
//
//}
