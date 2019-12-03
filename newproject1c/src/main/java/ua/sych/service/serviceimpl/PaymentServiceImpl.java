package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.PaymentRequest;
import ua.sych.dto.response.PaymentResponse;
import ua.sych.entity.Payment;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.PaymentRepository;
import ua.sych.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class PaymentServiceImpl {
    @Autowired
    private PaymentRepository paymentRepository;

      public void save(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setType(paymentRequest.getType());
        paymentRepository.save(payment);
    }

     public List<PaymentResponse> findAll() {
        return ObjectMapperUtils.mapAll(paymentRepository.findAll(),
                PaymentResponse.class);
    }

    public PaymentResponse findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Payment with id [" + id + "] not found"));

        return ObjectMapperUtils.map(payment, PaymentResponse.class);
    }

    public void update(Long id, PaymentRequest paymentRequest) {
        Payment paymentByDB = paymentRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Payment with " + id + " not found"));
        paymentByDB.setType(paymentRequest.getType());
        paymentRepository.save(paymentByDB);
    }

    public void delete(Long id) {
        Payment paymentByDB = paymentRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Payment with " + id + " not found"));
        paymentRepository.delete(paymentByDB);
    }
}
