package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.PartnerRequest;
import ua.sych.dto.response.PartnerResponse;
import ua.sych.service.serviceimpl.PartnerServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/partner")
public class PartnerController {
    @Autowired
    private PartnerServiceImpl partnerService;

    @GetMapping
    public ResponseEntity<?> getAllPartners() {
        List<PartnerResponse> partnerResponses = partnerService.findAllPartners();
        return new ResponseEntity<>(partnerResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPartner(@RequestBody @Valid PartnerRequest partner) {
        partnerService.savePartner(partner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{partnerId}")
    public ResponseEntity<?> getPartnerById(@PathVariable("partnerId") Long id) {
        PartnerResponse partnerResponse = partnerService.findById(id);
        return new ResponseEntity<>(partnerResponse, HttpStatus.OK);
    }

    @PutMapping("/{partnerId}")
    public ResponseEntity<?> updateById(@PathVariable("partnerId") Long id,
                                        @RequestBody PartnerRequest partnerRequest) {
        partnerService.update(id, partnerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        partnerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
