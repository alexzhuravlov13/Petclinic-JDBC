package com.zhuravlov.petclinic.dao;

import com.zhuravlov.petclinic.entity.Visit;
import com.zhuravlov.petclinic.util.DbUtil;

import java.sql.*;
import java.util.List;


public class VisitDAO implements DAO<Visit>
{

  private static final String INSERT_VISIT_SQL = "INSERT INTO visit (date, doctor, client, patient, description, change) VALUES (?, ?, ?, ?, ?, ?)";
  private static final String SELECT_VISIT_SQL = "SELECT visitId, date, doctor, client, patient, description, charge FROM visit WHERE id = ?";
  private static final String SELECT_ALL_FROM_VISIT = "SELECT * FROM visit";
  private static final String UPDATE_VISIT = "UPDATE visit SET date = ?, doctorId = ?, clientId = ?, petId = ?, description = ?, charge = ? WHERE id = ?";
  private static final String DELETE_VISIT = "DELETE FROM visit WHERE id = ?";


  @Override
  public Visit create(Visit visit)
  {

    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(INSERT_VISIT_SQL);
      ps.setDate(1, visit.getDate());
      ps.setLong(2, visit.getDoctor().getId());
      ps.setLong(3, visit.getClient().getId());
      ps.setLong(4, visit.getPet().getId());
      ps.setString(5, visit.getDescription());
      ps.setLong(4, visit.getCharge());

      ps.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return visit;
  }

  @Override
  public Visit read(long id)
  {
    Visit visit = null;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(SELECT_VISIT_SQL);
      ps.setLong(1, id);

      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next())
      {
        Date date = new Date(resultSet.getLong("date"));
        long doctorId = resultSet.getLong("doctor");
        long clientId = resultSet.getLong("client");
        long petId = resultSet.getLong("patient");
        String description = resultSet.getString("description");
        long charge = resultSet.getLong("charge");

        DoctorDAO doctorDAO = new DoctorDAO();
        ClientDAO clientDAO = new ClientDAO();
        PetDAO petDAO = new PetDAO();


        visit = new Visit(id, date, doctorDAO.read(doctorId), clientDAO.read(clientId), petDAO.read(petId), description, charge);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }

    return visit;
  }

  @Override
  public List<Visit> readAll()
  {

    List<Visit> visitList = null;
    try (Connection connection = DbUtil.getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_VISIT);

      while (resultSet.next())
      {
        long id = resultSet.getLong("visitId");
        Date date = new Date(resultSet.getLong("date"));
        long doctorId = resultSet.getLong("doctor");
        long clientId = resultSet.getLong("client");
        long petId = resultSet.getLong("patient");
        String description = resultSet.getString("description");
        long charge = resultSet.getLong("charge");

        DoctorDAO doctorDAO = new DoctorDAO();
        ClientDAO clientDAO = new ClientDAO();
        PetDAO petDAO = new PetDAO();


        Visit visit = new Visit(id, date, doctorDAO.read(doctorId), clientDAO.read(clientId), petDAO.read(petId), description, charge);
        visitList.add(visit);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }

    return visitList;
  }

  @Override
  public boolean update(Visit visit)
  {
    boolean update = false;

    try (Connection connection = DbUtil.getConnection())
    {

      PreparedStatement ps = connection.prepareStatement(UPDATE_VISIT);
      ps.setLong(1, visit.getDate().getTime());
      ps.setLong(2, visit.getDoctor().getId());
      ps.setLong(3, visit.getClient().getId());
      ps.setLong(4, visit.getPet().getId());
      ps.setString(5, visit.getDescription());
      ps.setLong(6, visit.getCharge());
      ps.setLong(7, visit.getId());
      ps.execute();

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
  public boolean delete(Visit visit)
  {
    boolean delete = false;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(DELETE_VISIT);
      ps.setLong(1, visit.getId());
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

