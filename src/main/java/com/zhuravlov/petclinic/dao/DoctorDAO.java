package com.zhuravlov.petclinic.dao;

import com.zhuravlov.petclinic.entity.Doctor;
import com.zhuravlov.petclinic.entity.Pet;
import com.zhuravlov.petclinic.util.DbUtil;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements DAO<Doctor>
{

  private static final String INSERT_DOCTOR_SQL = "INSERT INTO doctor (name, surname, birthDate, phoneNr) VALUES (?, ?, ?, ?)";
  private static final String SELECT_DOCTOR_SQL = "SELECT d.doctorId, d.dName, d.dSurname, d.dBirthDate, d.dPhoneNr, p.petId, p.petName, p.petBirthDate, p.type, p.masterId " +
      "FROM doctor d " +
      "INNER JOIN pet p WHERE d.doctorId = ?";
  private static final String SELECT_ALL_FROM_DOCTOR = "SELECT d.doctorId, d.dName, d.dSurname, d.dBirthDate, d.dPhoneNr, p.petId, p.petName, p.petBirthDate, p.type, p.masterId " +
      "FROM doctor d " +
      "INNER JOIN pet p";
  private static final String UPDATE_DOCTOR= "UPDATE doctor SET name=?, surname=?, birthDate=?, phoneNr=?, aID=? WHERE id=?";
  private static final String DELETE_DOCTOR = "DELETE FROM client WHERE id=?";



  @Override
  public Doctor create(Doctor doctor) {

    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(INSERT_DOCTOR_SQL);
      ps.setString(1, doctor.getName());
      ps.setString(2, doctor.getSurname());
      ps.setDate(3, doctor.getBirthDate());
      ps.setString(4, doctor.getPhoneNr());

      ps.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return doctor;
  }

  @Override
  public Doctor read(long id) {
    Doctor doctor = null;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_SQL);
      ps.setLong(1, id);
      ResultSet resultSet = ps.executeQuery();

      List<Pet> patients = new ArrayList<>();

      while (resultSet.next())
      {
        String name = resultSet.getString("dName");
        String surname = resultSet.getString("dSurname");
        Date birthDate = resultSet.getDate("dBirthDate");
        String phoneNr = resultSet.getString("dPhoneNr");

        long pId = resultSet.getLong("petId");
        String pName = resultSet.getString("petName");
        Date petBirthDate = resultSet.getDate("petBirthDate");
        String type = resultSet.getString("type");
        long masterId = resultSet.getLong("masterId");


        Pet pet = new Pet(pId, pName, petBirthDate, type, masterId);

        patients.add(pet);

        doctor = new Doctor(id, name, surname, birthDate, phoneNr, patients);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return doctor;
  }

  @Override
  public List<Doctor> readAll() {

    List<Doctor> clientList = new ArrayList<>();
    List<Pet> patients = new ArrayList<>();

    try (Connection connection = DbUtil.getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_DOCTOR);

      while (resultSet.next())
      {
        long id = resultSet.getLong("dId");
        String name = resultSet.getString("dName");
        String surname = resultSet.getString("dSurname");
        Date birthDate = resultSet.getDate("dBirthDate");
        String phoneNr = resultSet.getString("dPhoneNr");

        long pId = resultSet.getLong("petId");
        String pName = resultSet.getString("petName");
        Date petBirthDate = resultSet.getDate("petBirthDate");
        String type = resultSet.getString("type");
        long masterId = resultSet.getLong("masterId");


        Pet pet = new Pet(pId, pName, petBirthDate, type, masterId);

        patients.add(pet);

        Doctor doctor = new Doctor(id, name, surname, birthDate, phoneNr, patients);
        clientList.add(doctor);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return clientList;
  }

  @Override
  public boolean update(Doctor doctor) {

    boolean update = false;

    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(UPDATE_DOCTOR);
      ps.setString(1, doctor.getName());
      ps.setString(2, doctor.getSurname());
      ps.setDate(3, doctor.getBirthDate());
      ps.setString(4, doctor.getPhoneNr());

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
  public boolean delete(Doctor doctor) {
    boolean delete = false;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(DELETE_DOCTOR);
      ps.setLong(1, doctor.getId() );
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
