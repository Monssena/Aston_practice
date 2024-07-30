package org.practice.hw3.dao.impl;

import org.practice.hw3.dao.SpaceExpeditionDao;
import org.practice.hw3.dto.SpaceExpeditionsDto;
import org.practice.hw3.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceExpeditionDaoImpl implements SpaceExpeditionDao {
    @Override
    public void addExpedition(SpaceExpeditionsDto spaceExpeditionsDto) {
        final String ADD_EXPEDITION_SQL = "INSERT INTO space_expeditions(name, start_date, end_date) VALUES (?,?,?);";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(ADD_EXPEDITION_SQL)) {
                statement.setString(1, spaceExpeditionsDto.getName());
                statement.setDate(2, spaceExpeditionsDto.getStartDate());
                statement.setDate(3, spaceExpeditionsDto.getEndDate());
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
    public SpaceExpeditionsDto getById(int id) {
        final String GET_BY_ID_SQL = "SELECT * FROM space_expeditions WHERE id = ?;";
        SpaceExpeditionsDto spaceExpeditionsDto = null;

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_BY_ID_SQL)) {
            if (resultSet.next()) {
                spaceExpeditionsDto = new SpaceExpeditionsDto();
                spaceExpeditionsDto.setId(id);
                spaceExpeditionsDto.setName(resultSet.getString("name"));
                spaceExpeditionsDto.setStartDate(resultSet.getDate("start_date"));
                spaceExpeditionsDto.setEndDate(resultSet.getDate("end_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return spaceExpeditionsDto;
    }

    @Override
    public List<SpaceExpeditionsDto> getAllExpeditions() {
        final String GET_ALL_EXPEDITION_SQL = "SELECT * FROM space_expeditions;";
        List<SpaceExpeditionsDto> spaceExpeditionsDtoList = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_EXPEDITION_SQL)) {
            while (resultSet.next()) {
                SpaceExpeditionsDto spaceExpeditionsDto = new SpaceExpeditionsDto();
                spaceExpeditionsDto.setId(resultSet.getInt("id"));
                spaceExpeditionsDto.setName(resultSet.getString("name"));
                spaceExpeditionsDto.setStartDate(resultSet.getDate("start_date"));
                spaceExpeditionsDto.setEndDate(resultSet.getDate("end_date"));
                spaceExpeditionsDtoList.add(spaceExpeditionsDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return spaceExpeditionsDtoList;
    }

    @Override
    public void updateExpedition(SpaceExpeditionsDto spaceExpeditionsDto) {
        final String UPDATE_EXPEDITION = "UPDATE space_expeditions SET name = ?, start_date = ?, end_date = ? WHERE id = ?;";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(UPDATE_EXPEDITION)) {
                statement.setString(1, spaceExpeditionsDto.getName());
                statement.setDate(2, spaceExpeditionsDto.getStartDate());
                statement.setDate(3, spaceExpeditionsDto.getEndDate());
                statement.setInt(4, spaceExpeditionsDto.getId());
                statement.executeUpdate();
                connection.commit();
            }catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteExpedition(int id) {
        final String DELETE_EXPEDITION = "DELETE FROM space_expeditions WHERE id = ?;";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(DELETE_EXPEDITION)) {
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
}
