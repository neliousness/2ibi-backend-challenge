package com.neliolucas._2ibi.countryapi.services;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.repositories.CountryRepository;
import com.neliolucas._2ibi.countryapi.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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

    public boolean deleteCountry(long  uid)
    {
        if (countryRepository.findById(uid).isPresent())
        {
            countryRepository.deleteById(uid);

            return !countryRepository.findById(uid).isPresent();
        }

        return false;
    }

    public boolean update(long id,Country country)
    {
        Country foundCountry = countryRepository.findById(id).orElse(null);
        if (foundCountry != null)
        {
            if (foundCountry.hashCode() != country.hashCode()) {
                foundCountry.updateCountry(country);
                countryRepository.save(foundCountry);
                Helper.log(this.getClass(),"Country '" + foundCountry.getName()+"' updated");
                return true;
            }
            else
            {
                Helper.log(this.getClass(),"No changes in country '" + foundCountry.getName()+"'");
                return false;
            }

        }
        else
        {
            Helper.log(this.getClass(),"Country with id '" + id+"' does not exist");
            return false;
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

    public Country findByName(String name) {

        return countryRepository.findByName(name);
    }

    public Country findById(long id) {
        return countryRepository
                .findById(id)
                .orElse(null);
    }
}
