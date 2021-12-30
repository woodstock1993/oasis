package com.oasis.oasis_business.controller;

import com.oasis.oasis_business.domain.Contract;
import com.oasis.oasis_business.dto.ContractRequestDto;
import com.oasis.oasis_business.repository.ContractRepository;
import com.oasis.oasis_business.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class contractController {

    private final ContractRepository contractRepository;
    private final ContractService contractService;

    @GetMapping("/contracts")
    public List<Contract> getContracts() {
        return contractService.getContracts();
    }

    @PostMapping("/contracts")
    public Contract createContract(@RequestBody ContractRequestDto contractRequestDto) {
        Contract contract = contractService.createContract(contractRequestDto);
        return contract;
    }
}
