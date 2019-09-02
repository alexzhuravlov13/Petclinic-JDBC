package com.zhuravlov.petclinic.dao;

import com.zhuravlov.petclinic.entity.Address;
import com.zhuravlov.petclinic.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements DAO<Address>
{
  private static final String INSERT_ADDRESS_SQL = "INSERT INTO address (street, houseNr, apartmentNr, zip) VALUES (?, ?, ?, ?)";
  private static final String SELECT_ADDRESS_SQL = "SELECT * FROM address WHERE addressId =?";
  private static final String SELECT_ALL_FROM_ADRESS = "SELECT * FROM address";
  private static final String UPDATE_ADDRESS = "UPDATE address SET street=?, houseNr=?, apartmentNr=?, zip=? WHERE addressId=?";
  private static final String DELETE_ADDRESS = "DELETE FROM address WHERE addressId=?";

  @Override
  public Address create(Address address)
  {

    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(INSERT_ADDRESS_SQL);
      ps.setString(1, address.getStreet());
      ps.setString(2, address.getHouse());
      ps.setLong(3, address.getApartmentNr());
      ps.setLong(4, address.getZip());

      ps.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return address;
  }

  @Override
  public Address read(long id)
  {
    Address address = null;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(SELECT_ADDRESS_SQL);
      ps.setLong(1, id);
      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next())
      {
        String street = resultSet.getString("street");
        String house = resultSet.getString("houseNr");
        long apartmentNr = resultSet.getLong("apartmentNr");
        long zip = resultSet.getLong("zip");
        address = new Address(id, street, house, apartmentNr, zip);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return address;
  }

  @Override
  public List<Address> readAll()
  {
    List<Address> addressList = new ArrayList<>();
    try (Connection connection = DbUtil.getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_ADRESS);

      while (resultSet.next())
      {
        long id = resultSet.getLong("addressId");
        String street = resultSet.getString("street");
        String house = resultSet.getString("houseNr");
        long apartmentNr = resultSet.getLong("apartmentNr");
        long zip = resultSet.getLong("zip");
        Address address = new Address(id, street, house, apartmentNr, zip);
        addressList.add(address);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return addressList;
  }

  @Override
  public boolean update(Address address)
  {
    boolean update = false;

    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(UPDATE_ADDRESS);
      ps.setString(1, address.getStreet());
      ps.setString(2, address.getHouse());
      ps.setLong(3, address.getApartmentNr());
      ps.setLong(4, address.getZip());
      ps.setLong(5, address.getId());

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
  public boolean delete(Address address)
  {
    boolean delete = false;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(DELETE_ADDRESS);
      ps.setLong(1, address.getId() );
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
