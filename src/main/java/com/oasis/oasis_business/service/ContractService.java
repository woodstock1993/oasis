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

    public Contract getContract(Long id) {
        return contractRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("특정 계약 아이디가 없습니다.")
        );
    }

    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }

    @Transactional
    public Contract createContract(ContractRequestDto contractRequestDto) {
        Contract contract = new Contract(contractRequestDto);
        contractRepository.save(contract);
        return contract;
    }

    @Transactional
    public void updateContract(Long id, ContractRequestDto contractRequestDto) {
        Contract contract = contractRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("찾는 계약 아이디가 없습니다.")
        );
        contract.updateContract(contractRequestDto);
    }
}
