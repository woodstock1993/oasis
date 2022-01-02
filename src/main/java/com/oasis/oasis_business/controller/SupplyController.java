package com.oasis.oasis_business.controller;

import com.oasis.oasis_business.domain.Contract;
import com.oasis.oasis_business.domain.Supply;
import com.oasis.oasis_business.dto.SupplyRequestDto;
import com.oasis.oasis_business.repository.ContractRepository;
import com.oasis.oasis_business.repository.SupplyRepository;
import com.oasis.oasis_business.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController //Bean 등록
public class SupplyController {

    //Bean으로 만들려는 클래스들 중에 private final은 Bean으로 지정된 class이어야 한다.
    private final SupplyRepository supplyRepository;
    private final ContractRepository contractRepository;
    private final SupplyService supplyService;

    @GetMapping("/supplies/{id}")
    public List<Supply> getSupplies(@PathVariable Long id) {
        return supplyRepository.findAllByContractId(id);
    }

    @PostMapping("/supplies/{id}")
    public Supply createContract(@PathVariable Long id, @RequestBody SupplyRequestDto supplyRequestDto) {
        Contract contract = contractRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당하는 계약번호가 없습니다.")
        );
        Supply supply = new Supply(supplyRequestDto, contract);
        return supplyRepository.save(supply);
    }

    @PutMapping("/supplies/{id}")
    public Long updateSupply(@PathVariable Long id, @RequestBody SupplyRequestDto supplyRequestDto) {
        supplyService.updateSupply(id, supplyRequestDto);
        return id;
    }

    @DeleteMapping("/supplies/{id}")
    public void deleteSupply(@PathVariable Long id) {
        supplyRepository.deleteById(id);
    }
}
