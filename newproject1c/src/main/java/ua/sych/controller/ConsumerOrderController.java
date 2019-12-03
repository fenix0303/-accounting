package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.ConsumerOrderRequest;
import ua.sych.dto.response.ConsumerOrderResponse;
import ua.sych.service.serviceimpl.ConsumerOrderServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/consumer")
public class ConsumerOrderController {
    @Autowired
    private ConsumerOrderServiceImpl consumerOrderService;

    @PostMapping
    public ResponseEntity<?> createConsumerOrder(@RequestBody @Valid ConsumerOrderRequest consumerOrder) {
        consumerOrderService.saveConsumerOrder(consumerOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getConsumerOrders() {
        List<ConsumerOrderResponse> orders = consumerOrderService.findAllConsumerOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getConsumerOrderById(@PathVariable("orderId") Long id) {
        ConsumerOrderResponse order = consumerOrderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateConsumerOrderById(@PathVariable("orderId") Long id,
                                                     @RequestBody ConsumerOrderRequest consumerOrder) {
        consumerOrderService.updateConsumerOrder(id, consumerOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        consumerOrderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
