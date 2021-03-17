package com.neliolucas._2ibi.countryapi.repositories;

import com.neliolucas._2ibi.countryapi.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    @Query(value = "SELECT * FROM countries c WHERE c.name = ?1",nativeQuery = true)
    Country findByName(String name);

}
