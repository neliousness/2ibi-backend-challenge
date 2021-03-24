package com.neliolucas._2ibi.countryapi.services;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.repositories.CountryRepository;
import com.neliolucas._2ibi.countryapi.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Nelio
 * @date 18/03/2021
 */

@Service
@Transactional
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public boolean addCountry(Country country)
    {
        if (country.validCountry()) {
            if (countryRepository.findByName(country.getName()) == null) {
                countryRepository.save(country);

                Helper.logInfo(this,country.getName() + " added");
                return countryRepository.findByName(country.getName()) != null;
            }
            Helper.logInfo(this,country.getName() + " already exists");
            return false;
        }
        Helper.logInfo(this,country.getName() + " is not valid");
        return false;
    }

    public boolean deleteCountry(long  uid)
    {
        if (countryRepository.findById(uid).isPresent())
        {
            countryRepository.deleteById(uid);

            Helper.logInfo(this,"Country deleted");
            return !countryRepository.findById(uid).isPresent();
        }
        Helper.logInfo(this,"Unable to delete. Country not found");

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
                Helper.logInfo(this,"Country '" + foundCountry.getName()+"' updated");
                return true;
            }
            else
            {
                Helper.logInfo(this,"No changes for country '" + foundCountry.getName()+"'");
                return false;
            }

        }
        else
        {
            Helper.logInfo(this,"Country with id '" + id+"' does not exist");
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
