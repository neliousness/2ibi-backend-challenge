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

                Helper.log(this.getClass(),country.getName() + " added");
                return countryRepository.findByName(country.getName()) != null;
            }
            Helper.log(this.getClass(),country.getName() + " already exists");
            return false;
        }
        Helper.log(this.getClass(),country.getName() + " is not valid");
        return false;
    }

    public boolean deleteCountry(long  uid)
    {
        if (countryRepository.findById(uid).isPresent())
        {
            countryRepository.deleteById(uid);

            Helper.log(this.getClass(),"Country deleted");
            return !countryRepository.findById(uid).isPresent();
        }
        Helper.log(this.getClass(),"Unable to delete. Country not found");

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
                Helper.log(this.getClass(),"No changes for country '" + foundCountry.getName()+"'");
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
