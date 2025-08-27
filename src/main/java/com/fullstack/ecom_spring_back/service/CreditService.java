package com.fullstack.ecom_spring_back.service;

import com.fullstack.ecom_spring_back.entity.Client;
import com.fullstack.ecom_spring_back.entity.Credit;
import com.fullstack.ecom_spring_back.entity.Product;
import com.fullstack.ecom_spring_back.enums.Status;
import com.fullstack.ecom_spring_back.repository.ClientRepository;
import com.fullstack.ecom_spring_back.repository.CreditRepository;
import com.fullstack.ecom_spring_back.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    //Create Credit
    public Credit createCredit(Credit credit) {
        credit.setStatus(Status.PENDING);
        Product product = productRepository.findById(credit.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        Client client = clientRepository.findById(credit.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        credit.setPrice(product.getPrice());
        credit.setSellPrice(product.getSellPrice());
        return creditRepository.save(credit);
    }

    //Get All Credits
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    //Get Credit By Status
    public List<Credit> getCreditsByStatus(Status status) {
        return creditRepository.findByStatus(status);
    }

    //Get Credit By Client
    public List<Credit> getCreditByClient(Long clientId) {
        return creditRepository.findByClientId(clientId);
    }

    //Handle Credit
    public Credit updateCreditStatus(Long creditId, Status status) {
        Credit credit = creditRepository.findById(creditId).orElseThrow(() -> new RuntimeException("Credit not found"));
        credit.setStatus(status);
        return creditRepository.save(credit);
    }

    //Pay Credit
    public Credit handleCredit(Long id, Status status) {
        Credit credit = creditRepository.findById(id).orElseThrow(() -> new RuntimeException("Credit not found"));
        credit.setStatus(status);
        return creditRepository.save(credit);
    }
}
