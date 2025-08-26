package com.fullstack.ecom_spring_back.service;

import com.fullstack.ecom_spring_back.entity.Client;
import com.fullstack.ecom_spring_back.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //Add Client
    public Client addClient(Client client) {
        //check if name already exists
        if(clientRepository.existsByNameIgnoreCase(client.getName())) {
            throw new RuntimeException("Client with name '" + client.getName() + "' already exists");
        }
        if(clientRepository.existsByPhone(client.getPhone()) && client.getPhone() != null) {
            throw new RuntimeException("Client with phone '" + client.getPhone() + "' already exists");
        }
        return clientRepository.save(client);
    }

    //Update Client
    public Client updateClient(Long id, Client updated) {
        Client existing = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("No Client with id '" + id + "' Found"));
        //check if new name already exists (and not the same client)
        if(!existing.getName().equalsIgnoreCase(updated.getName()) && clientRepository.existsByNameIgnoreCase(updated.getName())) {
            throw new RuntimeException("Client with name '" + updated.getName() + "' already exists");
        }
        if(!existing.getPhone().equalsIgnoreCase(updated.getPhone()) && updated.getPhone() != null) {
            throw new RuntimeException("Client with phone '" + updated.getPhone() + "' already exists");
        }
        existing.setName(updated.getName());
        existing.setPhone(updated.getPhone());
        return clientRepository.save(existing);
    }

    //Get All Clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

}
