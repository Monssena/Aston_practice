package org.practice.hw3.dao.impl;

import org.practice.hw3.dao.CrewDao;
import org.practice.hw3.dto.CrewsDto;
import org.practice.hw3.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrewDaoImpl implements CrewDao {

    @Override
    public List<CrewsDto> getAllCrews() {
        final String sql = "select * from crews";
        List<CrewsDto> crews = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                CrewsDto crew = new CrewsDto();
                crew.setId(resultSet.getInt("id"));
                crew.setName(resultSet.getString("name"));
                crew.setCommander(resultSet.getString("commander"));
                crews.add(crew);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return crews;
    }

    @Override
    public void addCrew(CrewsDto crewsDto) {
        final String ADD_CREW = "insert into crews(name, commander) values(?,?);";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(ADD_CREW)) {
                statement.setString(1, crewsDto.getName());
                statement.setString(2, crewsDto.getCommander());
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCrew(int id) {
        final String DELETE_CREW = "delete from crews where id = ?;";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(DELETE_CREW)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CrewsDto getCrewById(int id) {
        final String GET_CREW_BY_ID = "select * from crews where id = ?;";
        CrewsDto crewsDto = null;

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CREW_BY_ID)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    crewsDto = new CrewsDto();
                    crewsDto.setId(resultSet.getInt("id"));
                    crewsDto.setName(resultSet.getString("name"));
                    crewsDto.setCommander(resultSet.getString("commander"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return crewsDto;
    }

    @Override
    public void updateCrew(CrewsDto crewsDto) {
        final String UPDATE_CREW = "update crews set name=?, commander=? where id = ?;";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CREW)) {
                statement.setString(1, crewsDto.getName());
                statement.setString(2, crewsDto.getCommander());
                statement.setInt(3, crewsDto.getId());
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
