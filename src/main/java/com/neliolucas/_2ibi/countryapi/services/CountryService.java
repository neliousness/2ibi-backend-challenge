package com.neliolucas._2ibi.countryapi.services;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public boolean addCountry(Country country)
    {
        if (countryRepository.findByName(country.getName()) == null)
        {
            countryRepository.save(country);

            return countryRepository.findByName(country.getName()) != null;
        }

        return false;
    }

    public boolean deleteCountry(Country country)
    {
        if (countryRepository.findByName(country.getName()) != null)
        {
            countryRepository.delete(country);

            return countryRepository.findByName(country.getName()) == null;
        }

        return false;
    }

    public void updateCountry(Country country)
    {
        if (countryRepository.findByName(country.getName()) != null)
        {
            countryRepository.save(country);

        }
    }

    public List<Country> findAll()
    {
        return countryRepository.findAll();
    }

    public List<Country> findAndSortAllByProperty(String property)
    {
        return countryRepository.findAll(Sort.by(property));
    }
}
