package com.neliolucas._2ibi.countryapi.api;


import com.neliolucas._2ibi.countryapi.CountryapiApplication;
import com.neliolucas._2ibi.countryapi.repositories.CountryRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class CountryResourceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CountryRepository countryRepository;
}
