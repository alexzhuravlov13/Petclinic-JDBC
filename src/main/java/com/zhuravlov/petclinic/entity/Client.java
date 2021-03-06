package com.zhuravlov.petclinic.entity;

import java.util.List;
import java.sql.Date;
import java.util.Objects;

public class Client
{
  private long id;
  private String name;
  private String surname;
  private Address address;
  private Date birthDate;
  private String phoneNr;
  private List <Pet> pets;

  public Client(String name, String surname, Address address, Date birthDate, String phoneNr, List<Pet>pets)
  {
    this(0, name, surname, address, birthDate, phoneNr, pets);
  }

  public Client(long id, String name, String surname, Address address, Date birthDate, String phoneNr, List <Pet> pets)
  {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.address = address;
    this.birthDate = birthDate;
    this.phoneNr = phoneNr;
    this.pets = pets;
  }

  public long getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getSurname()
  {
    return surname;
  }

  public Address getAddress()
  {
    return address;
  }

  public Date getBirthDate()
  {
    return birthDate;
  }

  public String getPhoneNr()
  {
    return phoneNr;
  }

  public List<Pet> getPets()
  {
    return pets;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setSurname(String surname)
  {
    this.surname = surname;
  }

  public void setAddress(Address address)
  {
    this.address = address;
  }

  public void setBirthDate(Date birthDate)
  {
    this.birthDate = birthDate;
  }

  public void setPhoneNr(String phoneNr)
  {
    this.phoneNr = phoneNr;
  }

  public void setPets(List<Pet> pets)
  {
    this.pets = pets;
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder("Client{");
    sb.append("id=").append(id).append(",\n");
    sb.append(" name='").append(name).append('\'').append(",\n");
    sb.append(" surname='").append(surname).append('\'').append(",\n");
    sb.append(" address=").append(address).append(",\n");
    sb.append(" birthDate=").append(birthDate).append(",\n");
    sb.append(" phoneNr='").append(phoneNr).append('\'').append(",\n");
    sb.append(" petsList='").append(pets.toString()).append('\'').append(",\n");
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    Client client = (Client) o;
    return id == client.id &&
        Objects.equals(name, client.name) &&
        Objects.equals(surname, client.surname) &&
        Objects.equals(address, client.address) &&
        Objects.equals(birthDate, client.birthDate) &&
        Objects.equals(phoneNr, client.phoneNr) &&
        Objects.equals(pets, client.pets);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, name, surname, address, birthDate, phoneNr, pets);
  }

  public void setId(long key)
  {

  }
}
