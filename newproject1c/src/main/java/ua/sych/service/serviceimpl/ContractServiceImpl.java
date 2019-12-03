package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.ContractRequest;
import ua.sych.dto.response.ContractResponse;
import ua.sych.entity.Contract;
import ua.sych.entity.Partner;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.ContractRepository;
import ua.sych.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class ContractServiceImpl {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PartnerServiceImpl partnerService;


    public void save(ContractRequest contractRequest) {
        Contract contract = new Contract();
        contract.setNumber(contractRequest.getNumber());
        contract.setDate(contractRequest.getDate());
        Partner partner = ObjectMapperUtils.map(partnerService
                .findById(contractRequest.getPartnerId()), Partner.class);
        contract.setPartner(partner);
        contractRepository.save(contract);
    }


    public List<ContractResponse> findAll() {
        List<Contract> contracts = contractRepository.findAll();
        return ObjectMapperUtils.mapAll(contracts, ContractResponse.class);
    }


    public ContractResponse findById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Contract with id ["
                        + id + "] not found"));

        return ObjectMapperUtils.map(contract, ContractResponse.class);
    }


    public void update(Long id, ContractRequest contractRequest) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Contract with "
                        + id + " not found"));
        contract.setNumber(contractRequest.getNumber());
        contract.setDate(contractRequest.getDate());
        Partner partner = ObjectMapperUtils.map(partnerService
                .findById(contractRequest.getPartnerId()), Partner.class);
        contract.setPartner(partner);
        contractRepository.save(contract);
    }


    public void delete(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Contract with "
                        + id + " not found"));
        contractRepository.delete(contract);
    }
}
