package com.zhuravlov.petclinic.dao;


import com.zhuravlov.petclinic.entity.Address;
import com.zhuravlov.petclinic.entity.Client;
import com.zhuravlov.petclinic.entity.Pet;
import com.zhuravlov.petclinic.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO implements DAO<Client>
{
  private static final String INSERT_ADDRESS_SQL = "INSERT INTO client (name, surname, birthDate, phoneNr, address) VALUES (?, ?, ?, ?, ?)";
  private static final String SELECT_CLIENT_SQL = "SELECT c.clientId, c.name, c.surname, c.birthDate, c.phoneNr, c.address, a.addressId, a.street, a.houseNr, a.apartmentNr, a.zip, p.petId, p.petName, p.petBirthDate, p.type, p.masterId " +
                                                  "FROM client c " +
                                                  "INNER JOIN address a on c.address = a.addressId " +
                                                  "INNER JOIN pet p WHERE c.clientId = ? and p.masterId = ?";
  private static final String SELECT_ALL_FROM_CLIENT = "SELECT c.clientId, c.name, c.surname, c.birthDate, c.phoneNr, c.address, a.addressId, a.street, a.houseNr, a.apartmentNr, a.zip, p.petId, p.petName, p.petBirthDate, p.type, p.masterId " +
                                                        "FROM client c " +
                                                        "INNER JOIN address a on c.address = a.addressId " +
                                                        "INNER JOIN pet p";
  private static final String UPDATE_CLIENT= "UPDATE client SET street=?, houseNr=?, apartmentNr=?, zip=? WHERE id=?";
  private static final String DELETE_ADDRESS = "DELETE FROM address WHERE id=?";



  @Override
  public Client create(Client client) {

    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(INSERT_ADDRESS_SQL);
      ps.setString(1, client.getName());
      ps.setString(2, client.getSurname());
      ps.setDate(3, client.getBirthDate());
      ps.setString(4, client.getPhoneNr());
      ps.setLong(5, client.getAddress().getId());


      ps.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return client;
  }


  @Override
  public Client read(long id) {
    Client client = null;
    try (Connection connection = DbUtil.getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(SELECT_CLIENT_SQL);
      ps.setLong(1, id);
      ps.setLong(2, id);
      ResultSet resultSet = ps.executeQuery();

      List<Pet> pets = new ArrayList<>();

      while (resultSet.next())
      {
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        Date birthDate = resultSet.getDate("birthDate");
        String phoneNr = resultSet.getString("phoneNr");

        long aId = resultSet.getLong("addressId");
        String street = resultSet.getString("street");
        String house = resultSet.getString("houseNr");
        long apartmentNr = resultSet.getLong("apartmentNr");
        long zip = resultSet.getLong("zip");


        long pId = resultSet.getLong("petId");
        String pName = resultSet.getString("petName");
        Date petBirthDate = resultSet.getDate("petBirthDate");
        String type = resultSet.getString("type");
        long masterId = resultSet.getLong("masterId");


        Pet pet = new Pet(pId, pName, petBirthDate, type, masterId);

        pets.add(pet);

        client = new Client(id, name, surname, new Address(aId, street, house, apartmentNr, zip), birthDate, phoneNr, pets);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return client;
  }

  @Override
  public List<Client> readAll() {
    List<Client> clientList = new ArrayList<>();
    List<Pet> pets = new ArrayList<>();

    try (Connection connection = DbUtil.getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_CLIENT);

      while (resultSet.next())
      {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        Date birthDate = resultSet.getDate("birthDate");
        String phoneNr = resultSet.getString("phoneNr");

        long aId = resultSet.getLong("addressId");
        String street = resultSet.getString("street");
        String house = resultSet.getString("houseNr");
        long apartmentNr = resultSet.getLong("apartmentNr");
        long zip = resultSet.getLong("zip");


        long pId = resultSet.getLong("petId");
        String pName = resultSet.getString("petName");
        Date petBirthDate = resultSet.getDate("petBirthDate");
        String type = resultSet.getString("type");
        long masterId = resultSet.getLong("masterId");


        Pet pet = new Pet(pId, pName, petBirthDate, type, masterId);

        pets.add(pet);

        Client client = new Client(id, name, surname, new Address(aId, street, house, apartmentNr, zip), birthDate, phoneNr, pets);
        clientList.add(client);
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
  public boolean update(Client client) {
    return false;
  }

  @Override
  public boolean delete(Client client) {
    return false;
  }
}
