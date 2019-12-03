package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.ProviderOrderRequest;
import ua.sych.dto.response.ProviderOrderResponse;
import ua.sych.service.serviceimpl.ProviderOrderImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/provider")
public class ProviderOrderController {
    @Autowired
    private ProviderOrderImpl providerOrderService;

    @PostMapping
    public ResponseEntity<?> createProviderOrder(
            @RequestBody @Valid ProviderOrderRequest providerOrder) {
        providerOrderService.save(providerOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<ProviderOrderResponse> orders = providerOrderService.findAllProviderOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<?> getProviderOrderById(@PathVariable("providerId") Long id) {
        ProviderOrderResponse order = providerOrderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{providerId}")
    public ResponseEntity<?> updateProviderOrderById(@PathVariable("providerId") Long id,
                                                     @RequestBody ProviderOrderRequest providerOrder) {
        providerOrderService.update(id, providerOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        providerOrderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
