package com.oasis.oasis_business.service;

import com.oasis.oasis_business.domain.Supply;
import com.oasis.oasis_business.dto.SupplyRequestDto;
import com.oasis.oasis_business.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SupplyService {
    private final SupplyRepository supplyRepository;

    public List<Supply> getSupplies(Long id) {
        return supplyRepository.findAllByContractId(id);
    }

    @Transactional
    public Supply createSupply(SupplyRequestDto supplyRequestDto) {
        Supply supply = new Supply(supplyRequestDto);
        supplyRepository.save(supply);
        return supply;
    }

    @Transactional
    public void updateSupply(Long id, SupplyRequestDto supplyRequestDto) {
        Supply supply = supplyRepository.findById(id).orElseThrow(
                () -> new NullPointerException("찾는 공급 아이디가 없습니다.")
        );
        supply.updateSupply(supplyRequestDto);
    }
}
