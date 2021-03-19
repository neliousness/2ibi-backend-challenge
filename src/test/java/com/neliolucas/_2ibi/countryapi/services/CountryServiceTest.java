package com.neliolucas._2ibi.countryapi.services;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.repositories.CountryRepository;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @author Nelio
 * @date 19/03/2021
 */

@RunWith(SpringRunner.class)
public class CountryServiceTest {

    @MockBean
    CountryService countryService;

    @MockBean
    CountryRepository countryRepository;

    @Test
    public void addValidCountry_returnTrue()
    {
        when(countryService.addCountry(any(Country.class))).thenReturn(true);

        Country country = new Country();
        assertThat(countryService.addCountry(country), equalTo(true));
    }

    @Test
    public void addInvalidCountry_returnFalse()
    {
        when(countryService.addCountry(any(Country.class))).thenReturn(false);
        Country country = new Country();
        country.setName(null);
        assertThat(countryService.addCountry(country), equalTo(false));
    }

    @Test
    public void findCountries_returnArrayWith5Items()
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

        List<Country> countries = Arrays.asList(moz,usa,china,alaska,germany);
        when(countryService.findAll()).thenReturn(countries);

        assertThat(countryService.findAll(),hasSize(5));
    }

    @Test
    public void deleteCountry_returnTrue()
    {
        when(countryService.deleteCountry(0)).thenReturn(true);
        assertThat(countryService.deleteCountry(0),equalTo(true));
    }

    @Test
    public void deleteCountry_returnFalse()
    {
        when(countryService.deleteCountry(0)).thenReturn(false);
        assertThat(countryService.deleteCountry(0),equalTo(false));
    }

    @Test
    public void updateCountry_returnTrue()
    {
        Country moz = new Country();
        moz.setName("Mozambique");
        moz.setArea(801590);
        moz.setCapital("Maputo");
        moz.setRegion("Africa");
        moz.setSubRegion("Eastern Africa");

        when(countryService.update(1,moz)).thenReturn(true);

        assertThat(countryService.update(1,moz),equalTo(true));
    }

    @Test
    public void updateCountry_returnFalse()
    {
        Country moz = new Country();
        moz.setName("Mozambique");
        moz.setArea(801590);
        moz.setCapital("Maputo");
        moz.setRegion("Africa");
        moz.setSubRegion("Eastern Africa");
        moz.setUid(0L);

        when(countryService.update(1L,moz)).thenReturn(false);

        assertThat(countryService.update(1L,moz),equalTo(false));
    }

    @Test
    public void findCountryByName_returnCountry()
    {
        Country moz = new Country();
        moz.setName("Mozambique");
        moz.setArea(801590);
        moz.setCapital("Maputo");
        moz.setRegion("Africa");
        moz.setSubRegion("Eastern Africa");
        when(countryService.findByName(moz.getName())).thenReturn(moz);

        assertThat(countryService.findByName(moz.getName()),equalTo(moz));
    }

    @Test
    public void findCountryByName_returnNull()
    {

        when(countryService.findByName("Bora Bora")).thenReturn(null);

        assertThat(countryService.findByName("Bora Bora"),equalTo(null));
    }


    @Test
    public void findCountryById_returnCountry()
    {
        Country moz = new Country();
        moz.setName("Mozambique");
        moz.setArea(801590);
        moz.setCapital("Maputo");
        moz.setRegion("Africa");
        moz.setSubRegion("Eastern Africa");

        when(countryService.findById(0L)).thenReturn(moz);

        assertThat(countryService.findById(0L),equalTo(moz));
    }

    @Test
    public void findCountryById_returnNull()
    {


        when(countryService.findById(0L)).thenReturn(null);

        assertThat(countryService.findById(0L),equalTo(null));
    }

    @Test
    public void sortCountriesByCapital_returnSortedListByCapital()
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

        List<Country> countries = Stream.of(moz,usa,china,alaska,germany)
                .sorted(Comparator.comparing(Country::getCapital))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("capital")).thenReturn(countries);

        assertThat(countryService.findAndSortAllByProperty("capital").get(0).getCapital(),equalTo("Beijing"));
    }

    @Test
    public void sortCountriesByName_returnSortedListByName()
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

        List<Country> countries = Stream.of(moz,usa,china,alaska,germany)
                .sorted(Comparator.comparing(Country::getName))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("name")).thenReturn(countries);

        assertThat(countryService.findAndSortAllByProperty("name").get(0).getName(),equalTo("Alaska"));
    }

    @Test
    public void sortCountriesByRegion_returnSortedListByRegion()
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

        List<Country> countries = Stream.of(moz,usa,china,alaska,germany)
                .sorted(Comparator.comparing(Country::getRegion))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("region")).thenReturn(countries);

        assertThat(countryService.findAndSortAllByProperty("region").get(0).getRegion(),equalTo("Africa"));
    }

    @Test
    public void sortCountriesBySubRegion_returnSortedListBySubRegion()
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

        List<Country> countries = Stream.of(moz,usa,china,alaska,germany)
                .sorted(Comparator.comparing(Country::getSubRegion))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("subRegion")).thenReturn(countries);

        assertThat(countryService.findAndSortAllByProperty("subRegion").get(0).getSubRegion(),equalTo("Central Europe"));
    }

    @Test
    public void sortCountriesByArea_returnSortedListByArea()
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

        List<Country> countries = Stream.of(moz,usa,china,alaska,germany)
                .sorted(Comparator.comparing(Country::getArea))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("area")).thenReturn(countries);

        assertThat(countryService.findAndSortAllByProperty("area").get(0).getArea(),equalTo(357022.0));
    }




}
