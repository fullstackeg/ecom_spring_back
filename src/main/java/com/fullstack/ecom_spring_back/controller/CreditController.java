package com.fullstack.ecom_spring_back.controller;

import com.fullstack.ecom_spring_back.entity.Credit;
import com.fullstack.ecom_spring_back.enums.Status;
import com.fullstack.ecom_spring_back.service.CreditService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@AllArgsConstructor
public class CreditController {
    private final CreditService creditService;

    //POST: Create Credit
    @PostMapping("/create")
    public ResponseEntity<Credit> createCredit(@RequestBody Credit credit) {
        return ResponseEntity.ok(creditService.createCredit(credit));
    }

    //GET: Get All Credits
    @GetMapping
    public ResponseEntity<List<Credit>> getAllCredits() {
        return ResponseEntity.ok(creditService.getAllCredits());
    }

    //GET: Get Credit By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Credit>> getCreditByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(creditService.getCreditsByStatus(status));
    }

    //GET: Get Credit By Client
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Credit>> getCreditByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getCreditByClient(clientId));
    }

    //POST: Handle Credit
    @PostMapping("/{creditId}/status/{status}")
    public ResponseEntity<Credit> handleCredit(@PathVariable Long creditId, @PathVariable Status status) {
        return ResponseEntity.ok(creditService.handleCredit(creditId, status));
    }
}
