package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.PaymentRequest;
import ua.sych.dto.response.PaymentResponse;
import ua.sych.service.serviceimpl.PaymentServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PaymentRequest paymentRequest) {
        paymentService.save(paymentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<PaymentResponse> payments = paymentService.findAll();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getById(@PathVariable("paymentId") Long id) {
        PaymentResponse paymentResponse = paymentService.findById(id);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<?> updateById(@PathVariable("paymentId") Long id,
                                        @RequestBody PaymentRequest paymentRequest) {
        paymentService.update(id, paymentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        paymentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
