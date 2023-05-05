package com.nekromant.telegram.service;

import com.nekromant.telegram.model.Contract;
import com.nekromant.telegram.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.time.LocalDate;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public Contract getContractByUsername(String username) throws InstanceNotFoundException {
        return contractRepository.findById(username).orElseThrow(() -> new InstanceNotFoundException("No contract bound to this username"));
    }

    public void updateContract(String username, String contractId, LocalDate date) throws InstanceNotFoundException {
        Contract contract = getContractByUsername(username);
        contract.setContractId(contractId);
        contract.setDate(date);
        saveContract(contract);
    }

    public void saveContract(Contract contract) {
        contractRepository.save(contract);
    }


}
