package com.fullstack.ecom_spring_back.controller;

import com.fullstack.ecom_spring_back.entity.Client;
import com.fullstack.ecom_spring_back.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //POST: Add Client
    @PostMapping("/create")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client saved = clientService.addClient(client);
        return ResponseEntity.ok(saved);
    }

    //POST: Update Client
    @PostMapping("/update/{id}")
    public ResponseEntity<Client> updateProduct(@PathVariable Long id, @RequestBody Client client) {
        Client updatedClient = clientService.updateClient(id, client);
        return ResponseEntity.ok(updatedClient);
    }

    //GET: All Products
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }
}
