package com.zhuravlov.petclinic.dao;

import com.zhuravlov.petclinic.entity.Doctor;
import com.zhuravlov.petclinic.entity.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorDAO implements DAO<Doctor>
{

  private static final String INSERT_DOCTOR_SQL = "INSERT INTO client (name, surname, birthDate, phoneNr, address) VALUES (?, ?, ?, ?, ?)";
  private static final String SELECT_DOCTOR_SQL = "SELECT c.clientId, c.name, c.surname, c.birthDate, c.phoneNr, c.address, a.addressId, a.street, a.houseNr, a.apartmentNr, a.zip, p.petId, p.petName, p.petBirthDate, p.type, p.masterId " +
      "FROM client c " +
      "INNER JOIN address a on c.address = a.addressId " +
      "INNER JOIN pet p WHERE c.clientId = ? and p.masterId = ?";
  private static final String SELECT_ALL_FROM_DOCTOR = "SELECT c.clientId, c.name, c.surname, c.birthDate, c.phoneNr, c.address, a.addressId, a.street, a.houseNr, a.apartmentNr, a.zip, p.petId, p.petName, p.petBirthDate, p.type, p.masterId " +
      "FROM client c " +
      "INNER JOIN address a on c.address = a.addressId " +
      "INNER JOIN pet p";
  private static final String UPDATE_DOCTOR= "UPDATE client SET name=?, surname=?, birthDate=?, phoneNr=?, aID=? WHERE id=?";
  private static final String DELETE_DOCTOR = "DELETE FROM client WHERE id=?";



  @Override
  public Doctor create(Doctor entity) {
    return null;
  }

  @Override
  public Doctor read(long id) {
    return null;
  }

  @Override
  public List<Doctor> readAll() {
    return null;
  }

  @Override
  public boolean update(Doctor entity) {
    return false;
  }

  @Override
  public boolean delete(Doctor entity) {
    return false;
  }
}
