package com.neliolucas._2ibi.countryapi.config;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import com.neliolucas._2ibi.countryapi.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    @Autowired
    CountryService countryService;

    @Bean
    public void insertDataIntoDB()
    {
        Country moz = new Country();
        moz.setName("moz");
        moz.setArea("moz");
        moz.setCapital("moz");
        moz.setRegion("region");
        moz.setSubRegion("fg");
        if(countryService.addCountry(moz))
        {
            Helper.log(this,"Country "+ moz.getName() + " added");
        }
    }
}
