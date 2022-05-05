package repository;

import models.Client;

import java.util.List;

public class ClientDAO {
    GenericDao generic;


    public ClientDAO()
    {
        generic=new GenericDao<Client>();
    }


    public List<Client> getClients()
    {
        generic.get();
    }
}
