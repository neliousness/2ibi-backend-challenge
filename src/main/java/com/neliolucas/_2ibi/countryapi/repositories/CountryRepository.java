package com.neliolucas._2ibi.countryapi.repositories;

import com.neliolucas._2ibi.countryapi.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * @author Nelio
 * @date 18/03/2021
 */

public interface CountryRepository extends JpaRepository<Country,Long> {

    @Query(value = "SELECT * FROM COUNTRIES  WHERE name = ?1",nativeQuery = true)
    Country findByName(String name);

}
