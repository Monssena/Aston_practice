package org.practice.hw3.dao;

import org.practice.hw3.dto.SpaceExpeditionsDto;

import java.util.List;

public interface SpaceExpeditionDao {
    void addExpedition(SpaceExpeditionsDto spaceExpeditionsDto);
    SpaceExpeditionsDto getById(int id);
    List<SpaceExpeditionsDto> getAllExpeditions();
    void updateExpedition(SpaceExpeditionsDto spaceExpeditionsDto);
    void deleteExpedition(int id);
}
