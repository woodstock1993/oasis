package com.oasis.oasis_business.service;

import com.oasis.oasis_business.domain.Contract;
import com.oasis.oasis_business.dto.ContractRequestDto;
import com.oasis.oasis_business.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }

    @Transactional
    public Contract createContract(ContractRequestDto contractRequestDto) {
        Contract contract = new Contract(contractRequestDto);
        contractRepository.save(contract);
        return contract;
    }
}
