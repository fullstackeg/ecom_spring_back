package com.fullstack.ecom_spring_back.service;

import com.fullstack.ecom_spring_back.entity.Product;
import com.fullstack.ecom_spring_back.entity.Sell;
import com.fullstack.ecom_spring_back.enums.Status;
import com.fullstack.ecom_spring_back.repository.ProductRepository;
import com.fullstack.ecom_spring_back.repository.SellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellService {
    private final SellRepository sellRepository;
    private final ProductRepository productRepository;

    //Create Sell
    public Sell createSell(Sell sell) {
        Product product = productRepository.findById(sell.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if(product.getStock() <= 0) {
            throw new RuntimeException("Product '" + product.getName() + "' is out of stock");
        }
        //reduce stock by 1
        product.setStock(product.getStock() - 1);
        productRepository.save(product);

        sell.setStatus(Status.PAID);
        sell.setPrice(product.getPrice());
        sell.setSellPrice(product.getSellPrice());
        return sellRepository.save(sell);
    }

    //Confirm Sell
    public Sell confirmSell(Long sellId) {
        Sell sell = sellRepository.findById(sellId).orElseThrow(() -> new RuntimeException("Sell not found"));
        sell.setStatus(Status.CONFIRMED);
        return sellRepository.save(sell);
    }

    //Cancel Sell
    public Sell cancelSell(Long sellId) {
        Sell sell = sellRepository.findById(sellId).orElseThrow(() -> new RuntimeException("Sell not found"));
        //Return stock to product
        Product product = sell.getProduct();
        product.setStock(product.getStock() + 1);
        productRepository.save(product);

        sell.setStatus(Status.CANCELED);
        return sellRepository.save(sell);
    }
}
