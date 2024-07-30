package org.practice.hw3.dao;

import org.practice.hw3.dto.CrewsDto;

import java.util.List;

public interface CrewDao {
    List<CrewsDto> getAllCrews();
    void addCrew(CrewsDto crewsDto);
    void deleteCrew(int id);
    CrewsDto getCrewById(int id);
    void updateCrew(CrewsDto crewsDto);
}
