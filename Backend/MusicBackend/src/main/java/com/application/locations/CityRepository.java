package com.application.locations;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City,Long> {
    public City findByID(Long ID);
    public City findByCityName(String cityName);
}
