package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.PartnerRequest;
import ua.sych.dto.response.PartnerResponse;
import ua.sych.entity.Enterprise;
import ua.sych.entity.Partner;
import ua.sych.entity.Payment;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.PartnerRepository;
import ua.sych.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerServiceImpl {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private EnterpriseServiceImpl enterpriseService;
    @Autowired
    private PaymentServiceImpl paymentService;

    public void savePartner(PartnerRequest partnerRequest) {
        Partner partner = new Partner();
        partner.setAddress(partnerRequest.getAddress());
        partner.setName(partnerRequest.getName());
        Enterprise enterprise = ObjectMapperUtils.map(enterpriseService
                .findById(partnerRequest.getEnterpriseId()), Enterprise.class);
        List<Payment> payments = new ArrayList<>();
        for (Long id : partnerRequest.getPaymentId()
        ) {
            payments.add(ObjectMapperUtils.map(paymentService.findById(id), Payment.class));
        }
        partner.setEnterprise(enterprise);
        partner.setPayments(payments);
        partnerRepository.save(partner);
    }

       public List<PartnerResponse> findAllPartners() {
        List<Partner> partners = partnerRepository.findAll();
        return ObjectMapperUtils.mapAll(partners, PartnerResponse.class);
    }

    public PartnerResponse findById(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Partner with id ["
                        + id + "] not found"));

        return ObjectMapperUtils.map(partner, PartnerResponse.class);
    }

      public void update(Long id, PartnerRequest partnerRequest) {
        Partner partnerByDB = partnerRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Partner with "
                        + id + " not found"));
        partnerByDB.setName(partnerRequest.getName());
        partnerByDB.setAddress(partnerRequest.getAddress());
        Enterprise enterprise = ObjectMapperUtils.map(enterpriseService
                .findById(partnerRequest.getEnterpriseId()), Enterprise.class);
        List<Payment> payments = new ArrayList<>();
        for (Long idPayment : partnerRequest.getPaymentId()
        ) {
            payments.add(ObjectMapperUtils.map(paymentService.findById(idPayment), Payment.class));
        }
        partnerByDB.setEnterprise(enterprise);
        partnerByDB.setPayments(payments);
        partnerRepository.save(partnerByDB);
    }

    public void delete(Long id) {
        Partner partnerByDB = partnerRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Partner with "
                        + id + " not found"));
        if (partnerByDB.getConsumerOrders().isEmpty()&&partnerByDB.getProviderOrders().isEmpty()) {
            partnerRepository.delete(partnerByDB);
        } else {
            throw new WrongInputDataException("Partner with " + id + " has orders");
        }

    }
}
