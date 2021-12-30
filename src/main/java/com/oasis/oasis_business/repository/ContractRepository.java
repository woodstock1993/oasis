package com.oasis.oasis_business.repository;

import com.oasis.oasis_business.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findAll();
}
