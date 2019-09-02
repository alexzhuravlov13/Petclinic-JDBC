package com.zhuravlov.petclinic;

import com.zhuravlov.petclinic.dao.AddressDAO;
import com.zhuravlov.petclinic.dao.ClientDAO;
import com.zhuravlov.petclinic.entity.Address;
import com.zhuravlov.petclinic.entity.Client;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        AddressDAO adressDAO = new AddressDAO();
      //create
      /*Address address = new Address("pedagogical", "20", 15, 65000);
      adressDAO.create(address);*/
      //read
      //Address address2 = adressDAO.read(3);
      //readall
      //List <Address> addressList = adressDAO.readAll();
      //System.out.println(addressList);

/*      Address address4 = addressList.get(3);
      Address address5 = addressList.get(4);
      Address address6 = addressList.get(5);
      Address address7 = addressList.get(6);

      adressDAO.delete(address4);
      adressDAO.delete(address5);
      adressDAO.delete(address6);
      adressDAO.delete(address7);*/

      //List <Address> addressList2 = adressDAO.readAll();
      //System.out.println(addressList2);

      ClientDAO clientDAO = new ClientDAO();
      Client client = clientDAO.read(1);
      System.out.println(client);



    }
}
