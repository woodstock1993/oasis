package com.oasis.oasis_business.repository;

import com.oasis.oasis_business.domain.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
    List<Supply> findAllByContractId(Long id);
}
