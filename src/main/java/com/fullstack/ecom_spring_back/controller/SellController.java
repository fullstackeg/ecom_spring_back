package com.fullstack.ecom_spring_back.controller;

import com.fullstack.ecom_spring_back.entity.Sell;
import com.fullstack.ecom_spring_back.repository.SellRepository;
import com.fullstack.ecom_spring_back.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sells")
@RequiredArgsConstructor
public class SellController {
    private final SellService sellService;
    private final SellRepository sellRepository;

    //POST: Create Sell
    @PostMapping("/create")
    public ResponseEntity<Sell> create(@RequestBody Sell sell) {
        Sell saved = sellService.createSell(sell);
        return ResponseEntity.ok(saved);
    }

    //POST: Confirm Sell
    @PostMapping("/confirm/{id}")
    public ResponseEntity<Sell> confirmSell(@PathVariable Long id) {
        Sell saved = sellService.confirmSell(id);
        return ResponseEntity.ok(saved);
    }

    //POST: Confirm Sell
    @PostMapping("/cancel/{id}")
    public ResponseEntity<Sell> cancelSell(@PathVariable Long id) {
        Sell saved = sellService.cancelSell(id);
        return ResponseEntity.ok(saved);
    }
}
