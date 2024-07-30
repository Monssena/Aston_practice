package org.practice.hw3.dao;

import org.practice.hw3.dto.CrewMemberDto;

import java.util.List;

public interface CrewMemberDao {
    void addCrewMember(CrewMemberDto crewMemberDto);
    void updateCrewMember(CrewMemberDto crewMemberDto);
    void deleteCrewMember(int id);
    CrewMemberDto getById(int id);
    List<CrewMemberDto> getAllCrewMembers();
}
