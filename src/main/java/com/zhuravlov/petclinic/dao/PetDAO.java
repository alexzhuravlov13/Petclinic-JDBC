package com.zhuravlov.petclinic.dao;


import com.zhuravlov.petclinic.entity.Pet;
import com.zhuravlov.petclinic.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PetDAO implements DAO<Pet>
{
  private static final String INSERT_PET_SQL = "INSERT INTO pet (name, birthDate, type, masterid) VALUES (?, ?, ?, ?)";


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
    return null;
  }

  @Override
  public List<Pet> readAll()
  {
    return null;
  }

  @Override
  public boolean update(Pet entity)
  {
    return false;
  }

  @Override
  public boolean delete(Pet entity)
  {
    return false;
  }
}
