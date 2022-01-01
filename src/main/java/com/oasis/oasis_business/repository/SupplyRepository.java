package com.oasis.oasis_business.repository;

import com.oasis.oasis_business.domain.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
    List<Supply> findAllByContractId(Long id);
}
