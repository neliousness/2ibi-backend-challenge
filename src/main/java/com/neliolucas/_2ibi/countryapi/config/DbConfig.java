package com.neliolucas._2ibi.countryapi.config;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import com.neliolucas._2ibi.countryapi.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

/**
 * @author Nelio
 * @date 18/03/2021
 */

@Configuration
public class DbConfig {

    @Autowired
    CountryService countryService;

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Bean
    public void insertDataIntoDB()
    {
        Country moz = new Country();
        moz.setName("Mozambique");
        moz.setArea(801590);
        moz.setCapital("Maputo");
        moz.setRegion("Africa");
        moz.setSubRegion("Eastern Africa");

        Country usa = new Country();
        usa.setName("United States");
        usa.setArea(9833520);
        usa.setCapital("Washington DC");
        usa.setRegion("America");
        usa.setSubRegion("North America");

        Country china = new Country();
        china.setName("China");
        china.setArea(9596961);
        china.setCapital("Beijing");
        china.setRegion("Asia");
        china.setSubRegion("Eastern Asia");

        Country germany = new Country();
        germany.setName("Germany");
        germany.setArea(357022);
        germany.setCapital("Berlin");
        germany.setRegion("Europe");
        germany.setSubRegion("Central Europe");

        Country alaska = new Country();
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital("Juneau");
        alaska.setRegion("America");
        alaska.setSubRegion("Northwest America");


        if(countryService.addCountry(moz))
        {
            Helper.log(this.getClass(),"Country "+ moz.getName() + " added");
        }

        if(countryService.addCountry(usa))
        {
            Helper.log(this.getClass(),"Country "+ usa.getName() + " added");
        }

        if(countryService.addCountry(china))
        {
            Helper.log(this.getClass(),"Country "+ china.getName() + " added");
        }

        if(countryService.addCountry(germany))
        {
            Helper.log(this.getClass(),"Country "+ germany.getName() + " added");
        }

        if(countryService.addCountry(alaska))
        {
            Helper.log(this.getClass(),"Country "+ alaska.getName() + " added");
        }

    }
}
