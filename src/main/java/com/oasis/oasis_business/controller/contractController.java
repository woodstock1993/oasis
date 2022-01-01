package com.oasis.oasis_business.controller;

import com.oasis.oasis_business.domain.Contract;
import com.oasis.oasis_business.dto.ContractRequestDto;
import com.oasis.oasis_business.repository.ContractRepository;
import com.oasis.oasis_business.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor //인수가 2개인 private final 생성자를 만들어준다.
@RestController
public class contractController {

    private final ContractService contractService; //꼭 필요하다.
    private final ContractRepository contractRepository;

    @GetMapping("/contracts")
    public List<Contract> getContracts() {
        return contractService.getContracts();
    }

    @GetMapping("/contracts/{id}")
    public Contract getContract(@PathVariable Long id) {
        Contract contract = contractService.getContract(id);
        return contract;
    }

    @PostMapping("/contracts")
    public Contract createContract(@RequestBody ContractRequestDto contractRequestDto) {
        Contract contract = contractService.createContract(contractRequestDto);
        return contract;
    }

    @PutMapping("/contracts/{id}")
    public Long updateContract(@PathVariable Long id, @RequestBody ContractRequestDto contractRequestDto) {
        contractService.updateContract(id, contractRequestDto);
        return id;
    }

    @DeleteMapping("/contracts/{id}")
    public Long deleteContract(@PathVariable Long id) {
        contractRepository.deleteById(id);
        return id;
    }
}
