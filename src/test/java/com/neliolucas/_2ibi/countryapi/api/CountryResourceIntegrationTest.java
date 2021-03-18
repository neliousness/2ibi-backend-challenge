package com.neliolucas._2ibi.countryapi.api;


import com.google.gson.Gson;
import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CountryResource.class)
@RunWith(SpringRunner.class)
public class CountryResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;



    @Test
    public void fetchAllCountries() throws Exception {

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
    public void addCountry() throws Exception {

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
    public void fetchCountriesSortedByCapital() throws Exception {

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

        List<Country> countriesSortedByCapital = Arrays.asList(china,germany,alaska,moz,usa);

        when(countryService.findAndSortAllByProperty("capital")).thenReturn(countriesSortedByCapital);

        mockMvc.perform(get("/api/country/all/sortedBy/capital")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].capital",is("Beijing")))
                .andExpect(jsonPath("$[1].capital",is("Berlin")))
                .andExpect(jsonPath("$[2].capital",is("Juneau")))
                .andExpect(jsonPath("$[3].capital",is("Maputo")))
                .andExpect(jsonPath("$[4].capital",is("Washington DC")));
    }

//    @Test
//    void fetchSingleCountry() {
//    }

//    @Test
//    void delteCountry() {
//    }
//
//    @Test
//    void updateSingleCountry() {
//    }

}
