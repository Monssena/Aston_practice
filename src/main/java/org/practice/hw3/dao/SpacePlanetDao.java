package org.practice.hw3.dao;

import org.practice.hw3.dto.SpacePlanetDto;

import java.util.List;

public interface SpacePlanetDao {
    void addSpacePlanet(SpacePlanetDto spacePlanet);
    void updateSpacePlanet(SpacePlanetDto spacePlanet);
    void deleteSpacePlanet(int id);
    SpacePlanetDto getById(int id);
    List<SpacePlanetDto> getAllSpacePlanets();
}
