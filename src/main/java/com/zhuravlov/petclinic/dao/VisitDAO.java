package com.zhuravlov.petclinic.dao;

import com.zhuravlov.petclinic.entity.Visit;

import java.util.List;


public class VisitDAO implements DAO<Visit>
{

  @Override
  public Visit create(Visit entity) {
    return null;
  }

  @Override
  public Visit read(long id) {
    return null;
  }

  @Override
  public List<Visit> readAll() {
    return null;
  }

  @Override
  public boolean update(Visit entity) {
    return false;
  }

  @Override
  public boolean delete(Visit entity) {
    return false;
  }
}
