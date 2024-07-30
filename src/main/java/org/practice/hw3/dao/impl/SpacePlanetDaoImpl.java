package org.practice.hw3.dao.impl;

import org.practice.hw3.dao.SpacePlanetDao;
import org.practice.hw3.dto.SpacePlanetDto;
import org.practice.hw3.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpacePlanetDaoImpl implements SpacePlanetDao {

    @Override
    public void addSpacePlanet(SpacePlanetDto spacePlanet) {
        final String ADD_PLANET = "insert into space_planet (name, type) values (?,?)";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(ADD_PLANET)) {
                statement.setString(1, spacePlanet.getName());
                statement.setString(2, spacePlanet.getType());
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
    public void updateSpacePlanet(SpacePlanetDto spacePlanet) {
        final String UPDATE_PLANET = "update space_planet set name=?,type=? where id=?";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(UPDATE_PLANET)) {
                statement.setString(1, spacePlanet.getName());
                statement.setString(2, spacePlanet.getType());
                statement.setInt(3, spacePlanet.getId());
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
    public void deleteSpacePlanet(int id) {
        final String DELETE_PLANET = "delete from space_planet where id=?";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(DELETE_PLANET)) {
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
    public SpacePlanetDto getById(int id) {
        final String GET_PLANET = "select * from space_planet where id=?";
        SpacePlanetDto spacePlanetDto = null;

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_PLANET)) {

            if (resultSet.next()) {
                spacePlanetDto = new SpacePlanetDto();
                spacePlanetDto.setId(id);
                spacePlanetDto.setName(resultSet.getString("name"));
                spacePlanetDto.setType(resultSet.getString("type"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return spacePlanetDto;
    }

    @Override
    public List<SpacePlanetDto> getAllSpacePlanets() {
        final String GET_ALL_PLANET = "select * from space_planet";
        List<SpacePlanetDto> spacePlanetDtos = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_PLANET)) {
            while (resultSet.next()) {
                SpacePlanetDto spacePlanetDto = new SpacePlanetDto();
                spacePlanetDto.setId(resultSet.getInt("id"));
                spacePlanetDto.setName(resultSet.getString("name"));
                spacePlanetDto.setType(resultSet.getString("type"));
                spacePlanetDtos.add(spacePlanetDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return spacePlanetDtos;
    }
}
