package com.neliolucas._2ibi.countryapi.api;


import com.google.gson.Gson;
import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Nelio
 * @date 19/03/2021
 */

@WebMvcTest(CountryResource.class)
@RunWith(SpringRunner.class)
public class CountryResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;



    @Test
    public void fetchAllCountries_returnResponseOKAndCountryListSize5() throws Exception {

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

        //when
        when(countryService.findAll()).thenReturn(countries);

        mockMvc.perform(get("/api/country/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].name", is("Mozambique")))
                .andExpect(jsonPath("$[1].name", is("United States")))
                .andExpect(jsonPath("$[2].name", is("China")))
                .andExpect(jsonPath("$[3].name", is("Alaska")))
                .andExpect(jsonPath("$[4].name", is("Germany")));

    }


    @Test
    public void addValidCountry_returnResponseCreated() throws Exception {

        Country alaska = new Country();
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital("Juneau");
        alaska.setRegion("America");
        alaska.setSubRegion("Northwest America");


        //when
        when(countryService.addCountry(alaska)).thenReturn(true);

        mockMvc.perform(post("/api/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(alaska)))
                .andExpect(status().isCreated());
    }

    @Test
    public void failAddCountryWithInvalidCapital_returnResponseBadRequest() throws Exception {

        Country alaska = new Country();
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital(null);
        alaska.setRegion("America");
        alaska.setSubRegion("Northwest America");


        //when
        when(countryService.addCountry(alaska)).thenReturn(false);

        mockMvc.perform(post("/api/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(alaska)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void failAddCountryWithInvalidName_returnResponseBadRequest() throws Exception {

        Country alaska = new Country();
        alaska.setName(null);
        alaska.setArea(1717856);
        alaska.setCapital("Juneau");
        alaska.setRegion("America");
        alaska.setSubRegion("Northwest America");


        //when
        when(countryService.addCountry(alaska)).thenReturn(false);

        mockMvc.perform(post("/api/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(alaska)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void failAddCountryWithInvalidRegion_returnResponseBadRequest() throws Exception {

        Country alaska = new Country();
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital("Juneau");
        alaska.setRegion(null);
        alaska.setSubRegion("Northwest America");


        //when
        when(countryService.addCountry(alaska)).thenReturn(false);

        mockMvc.perform(post("/api/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(alaska)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void failAddCountryWithInvalidSubRegion_returnResponseBadRequest() throws Exception {

        Country alaska = new Country();
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital("Juneau");
        alaska.setRegion("America");
        alaska.setSubRegion(null);


        //when
        when(countryService.addCountry(alaska)).thenReturn(false);

        mockMvc.perform(post("/api/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(alaska)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void fetchCountriesSortedByCapital_returnResponseFound() throws Exception {

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

        List<Country> countriesSortedByCapital = Stream.of(usa,germany,china,alaska,moz)
                .sorted(Comparator.comparing(Country::getCapital))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("capital")).thenReturn(countriesSortedByCapital);

        mockMvc.perform(get("/api/country/all/sortedBy/capital")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].capital",is("Beijing")));

    }

    @Test
    public void fetchCountriesSortedByName_returnResponseFound() throws Exception {

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

        List<Country> countriesSortedByCapital = Stream.of(usa,germany,china,alaska,moz)
                .sorted(Comparator.comparing(Country::getName))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("name")).thenReturn(countriesSortedByCapital);

        mockMvc.perform(get("/api/country/all/sortedBy/name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].name",is("Alaska")));
    }

    @Test
    public void fetchCountriesSortedByRegion_returnResponseFound() throws Exception {

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

        List<Country> countriesSortedByCapital = Stream.of(usa,germany,china,alaska,moz)
                .sorted(Comparator.comparing(Country::getRegion))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("region")).thenReturn(countriesSortedByCapital);

        mockMvc.perform(get("/api/country/all/sortedBy/region")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].region",is("Africa")));
    }

    @Test
    public void fetchCountriesSortedBySubRegion_returnResponseFound() throws Exception {

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

        List<Country> countriesSortedByCapital = Stream.of(usa,germany,china,alaska,moz)
                .sorted(Comparator.comparing(Country::getSubRegion))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("subRegion")).thenReturn(countriesSortedByCapital);

        mockMvc.perform(get("/api/country/all/sortedBy/subRegion")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].subRegion",is("Central Europe")));
    }

    @Test
    public void fetchCountriesSortedByArea_returnResponseFound() throws Exception {

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

        List<Country> countriesSortedByCapital = Stream.of(usa,germany,china,alaska,moz)
                .sorted(Comparator.comparing(Country::getArea))
                .collect(Collectors.toList());

        when(countryService.findAndSortAllByProperty("area")).thenReturn(countriesSortedByCapital);

        mockMvc.perform(get("/api/country/all/sortedBy/area")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].area",is(357022.0)));
    }

    @Test
    public void fetchCountriesSortedByInvalidProperty_returnResponseBadRequest() throws Exception {


        when(countryService.findAndSortAllByProperty("state")).thenThrow(PropertyReferenceException.class);

        mockMvc.perform(get("/api/country/all/sortedBy/state")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void fetchSingleCountryByValidId_returnResponseIsFound() throws Exception {
        Country alaska = new Country();
        alaska.setUid(0L);
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital("Juneau");
        alaska.setRegion("America");
        alaska.setSubRegion("Northwest America");


        //when
        when(countryService.findById(0)).thenReturn(alaska);

        mockMvc.perform(get("/api/country/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(content().string(containsString("Alaska")));

    }

    @Test
    public void fetchSingleCountryByInvalidId_returnResponseNotFound() throws Exception {


        //when
        when(countryService.findById(0)).thenReturn(null);

        mockMvc.perform(get("/api/country/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deleteCountryWithValidId_returnResponseOK() throws Exception {
        when(countryService.deleteCountry(4L)).thenReturn(true);
        mockMvc.perform(delete("/api/country/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void failDeleteWithInValidCountryId_returnResponseNotFound() throws Exception {
        when(countryService.deleteCountry(4L)).thenReturn(false);
        mockMvc.perform(delete("/api/country/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
   public void updateCountryWithValidBody_returnResponseOk() throws Exception{

        Country alaska = new Country();
        alaska.setUid(0L);
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital("Juneau");
        alaska.setRegion("America");
        alaska.setSubRegion("Northwest America");


        when(countryService.update(alaska.getUid(),alaska)).thenReturn(true);

        mockMvc.perform(put("/api/country/"+alaska.getUid()).contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(alaska)))
                .andExpect(status().isOk());
    }

    @Test
    public void failUpdateCountryWithInvalidBody_returnResponseBadRequest() throws Exception{

        Country alaska = new Country();
        alaska.setUid(0L);
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital(null);
        alaska.setRegion("America");
        alaska.setSubRegion("Northwest America");


        when(countryService.update(alaska.getUid(),alaska)).thenReturn(true);

        mockMvc.perform(put("/api/country/"+alaska.getUid()).contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(alaska)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void failUpdateCountryWithValidBodyAndInvalidId_returnResponseNotFound() throws Exception{

        Country alaska = new Country();
        alaska.setUid(8L);
        alaska.setName("Alaska");
        alaska.setArea(1717856);
        alaska.setCapital("Juneau");
        alaska.setRegion("America");
        alaska.setSubRegion("Northwest America");


        when(countryService.update(alaska.getUid(),alaska)).thenReturn(false);

        mockMvc.perform(put("/api/country/"+alaska.getUid()).contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(alaska)))
                .andExpect(status().isNotFound());
    }

}
