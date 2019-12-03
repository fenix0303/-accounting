package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.EnterpriseRequest;
import ua.sych.dto.response.EnterpriseResponse;
import ua.sych.service.serviceimpl.EnterpriseServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseServiceImpl enterpriseService;

    @GetMapping
    public ResponseEntity<?> getEnterprises() {
        List<EnterpriseResponse> enterprises = enterpriseService.findAllEnterprise();
        return new ResponseEntity<>(enterprises, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEnterprise(
            @RequestBody @Valid EnterpriseRequest enterprise) {
        enterpriseService.saveEnterprise(enterprise);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{enterpriseId}")
    public ResponseEntity<?> getById(@PathVariable("enterpriseId") Long id) {
        EnterpriseResponse enterpriseResponse = enterpriseService.findById(id);
        return new ResponseEntity<>(enterpriseResponse, HttpStatus.OK);
    }

    @PutMapping("/{enterpriseId}")
    public ResponseEntity<?> updateUserById(@PathVariable("enterpriseId") Long id,
                                            @RequestBody EnterpriseRequest enterpriseRequest) {
        enterpriseService.updateEnterprise(id, enterpriseRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        enterpriseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
