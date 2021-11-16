package com.example.weatherforecastapp.repository;

import com.example.weatherforecastapp.entity.LocationEntity;
import com.example.weatherforecastapp.entity.UserEntity;
import com.example.weatherforecastapp.entity.WeatherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends CrudRepository<LocationEntity, Long> {

    LocationEntity findByCity(String city);
}
