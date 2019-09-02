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
