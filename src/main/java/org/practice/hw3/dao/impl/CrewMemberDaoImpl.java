package org.practice.hw3.dao.impl;

import org.practice.hw3.dao.CrewMemberDao;
import org.practice.hw3.dto.CrewMemberDto;
import org.practice.hw3.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrewMemberDaoImpl implements CrewMemberDao {
    @Override
    public void addCrewMember(CrewMemberDto crewMemberDto) {
        final String ADD_MEMBER = "insert into crew_members(name, rank, crew_id) values(?,?,?);";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEMBER)) {
                preparedStatement.setString(1, crewMemberDto.getName());
                preparedStatement.setInt(2, crewMemberDto.getRank());
                preparedStatement.setInt(3, crewMemberDto.getCrewId());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCrewMember(CrewMemberDto crewMemberDto) {
        final String UPDATE_MEMBER = "update crew_members set name=?, rank=?, crew_id=? where id=?;";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEMBER)) {
                preparedStatement.setString(1, crewMemberDto.getName());
                preparedStatement.setInt(2, crewMemberDto.getRank());
                preparedStatement.setInt(3, crewMemberDto.getCrewId());
                preparedStatement.setInt(4, crewMemberDto.getId());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCrewMember(int id) {
        final String DELETE_MEMBER = "delete from crew_members where id=?;";

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEMBER)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CrewMemberDto getById(int id) {
        final String GET_BY_ID = "select * from crew_members where id=?;";
        CrewMemberDto crewMemberDto = null;

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    crewMemberDto = new CrewMemberDto();
                    crewMemberDto.setId(resultSet.getInt("id"));
                    crewMemberDto.setName(resultSet.getString("name"));
                    crewMemberDto.setRank(resultSet.getInt("rank"));
                    crewMemberDto.setCrewId(resultSet.getInt("crew_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return crewMemberDto;
    }

    @Override
    public List<CrewMemberDto> getAllCrewMembers() {
        final String GET_ALL_CREWMEMBERS = "select * from crew_members;";
        List<CrewMemberDto> crewMemberDtos = null;

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(GET_ALL_CREWMEMBERS)) {
                crewMemberDtos = new ArrayList<>();

                while (resultSet.next()) {
                    CrewMemberDto crewMemberDto = new CrewMemberDto();
                    crewMemberDto.setId(resultSet.getInt("id"));
                    crewMemberDto.setName(resultSet.getString("name"));
                    crewMemberDto.setRank(resultSet.getInt("rank"));
                    crewMemberDto.setCrewId(resultSet.getInt("crew_id"));
                    crewMemberDtos.add(crewMemberDto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return crewMemberDtos;
    }
}
