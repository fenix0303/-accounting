package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.ContractRequest;
import ua.sych.dto.response.ContractResponse;
import ua.sych.service.serviceimpl.ContractServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractServiceImpl contractService;

    @PostMapping
    public ResponseEntity<?> createContract(@RequestBody @Valid ContractRequest contract) {
        contractService.save(contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllContracts() {
        List<ContractResponse> contracts = contractService.findAll();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<?> getContractById(@PathVariable("contractId") Long id) {
        ContractResponse contractResponse = contractService.findById(id);
        return new ResponseEntity<>(contractResponse, HttpStatus.OK);
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<?> updateContractById(@PathVariable("contractId") Long id,
                                                @RequestBody ContractRequest contract) {
        contractService.update(id, contract);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        contractService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
