package com.zhuravlov.petclinic.dao;


import com.zhuravlov.petclinic.entity.Pet;
import com.zhuravlov.petclinic.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PetDAO implements DAO<Pet>
{
  private static final String INSERT_PET_SQL = "INSERT INTO pet (petName, petBirthDate, type, masterId) VALUES (?, ?, ?, ?)";
  private static final String SELECT_PET_SQL = "SELECT * FROM pet WHERE petId=?";
  private static final String SELECT_ALL_FROM_PET = "SELECT * FROM pet";
  private static final String UPDATE_PET = "UPDATE pet SET petName=?, petBirthDate=?, type=?, masterId=? WHERE id=?";
  private static final String DELETE_PET = "DELETE FROM pet WHERE id=?";

  @Override
  public Pet create(Pet pet)
  {
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(INSERT_PET_SQL);
      ps.setString(1, pet.getName());
      ps.setDate(2, pet.getBirthDate());
      ps.setString(3, pet.getType());
      ps.setLong(4, pet.getMasterId());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  @Override
  public Pet read(long id)
  {
    Pet pet = null;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(SELECT_PET_SQL);
      ps.setLong(1, id);

      ResultSet resultSet = ps.executeQuery();

      List<Pet> pets = new ArrayList<>();

      while (resultSet.next())
      {
        String name = resultSet.getString("petName");
        Date birthDate = resultSet.getDate("petBirthDate");
        String type = resultSet.getString("type");
        Long masterId = resultSet.getLong("masterId");

        pet = new Pet(id, name, birthDate, type, masterId);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return pet;
  }

  @Override
  public List<Pet> readAll()
  {
    List<Pet> petList = new ArrayList<>();

    try (Connection connection = DbUtil.getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PET);

      while (resultSet.next())
      {
        long id = resultSet.getLong("petId");
        String name = resultSet.getString("petName");
        Date birthDate = resultSet.getDate("petBirthDate");
        String type = resultSet.getString("type");
        Long masterId = resultSet.getLong("masterId");

        Pet pet = new Pet(id, name, birthDate, type, masterId);
        petList.add(pet);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return petList;
  }

  @Override
  public boolean update(Pet pet)
  {
    boolean update = false;

    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(UPDATE_PET);
      ps.setString(1, pet.getName());
      ps.setDate(3, pet.getBirthDate());
      ps.setString(4, pet.getType());
      ps.setLong(5, pet.getMasterId());

      ps.executeUpdate();
      update = true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      update = false;
    }
    return update;
  }

  @Override
  public boolean delete(Pet pet)
  {

    boolean delete = false;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(DELETE_PET);
      ps.setLong(1, pet.getId() );
      ps.executeUpdate();
      delete = true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      delete = false;
    }
    return delete;
  }
}
