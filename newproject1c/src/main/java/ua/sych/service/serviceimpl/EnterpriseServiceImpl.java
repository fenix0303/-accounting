package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.EnterpriseRequest;
import ua.sych.dto.response.EnterpriseResponse;
import ua.sych.entity.Enterprise;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.EnterpriseRepository;
import ua.sych.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class EnterpriseServiceImpl{
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public void saveEnterprise(EnterpriseRequest enterpriseRequest) {
        Enterprise enterprise = new Enterprise();
        enterprise.setName(enterpriseRequest.getName());
        enterprise.setAddress(enterpriseRequest.getAddress());
        enterprise.setTaxNumber(enterpriseRequest.getTaxNumber());
        enterpriseRepository.save(enterprise);
    }

    public List<EnterpriseResponse> findAllEnterprise() {
        return ObjectMapperUtils.mapAll(enterpriseRepository.findAll(), EnterpriseResponse.class);
    }

    public void updateEnterprise(Long id, EnterpriseRequest enterpriseRequest) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Enterprise with "
                        + id + " not found"));
        enterprise.setName(enterpriseRequest.getName());
        enterprise.setAddress(enterpriseRequest.getAddress());
        enterprise.setTaxNumber(enterpriseRequest.getTaxNumber());
        enterpriseRepository.save(enterprise);
    }

    public void delete(Long id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Enterprise with "
                        + id + " not found"));
        enterpriseRepository.delete(enterprise);
    }

    public EnterpriseResponse findById(Long id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Enterprise with id [" + id + "] not found"));

        return ObjectMapperUtils.map(enterprise, EnterpriseResponse.class);
    }
}
