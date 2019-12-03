package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.InventoryRequest;
import ua.sych.dto.response.InventoryResponse;
import ua.sych.service.serviceimpl.InventoryServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryServiceImpl inventoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid InventoryRequest inventory) {
        inventoryService.save(inventory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<InventoryResponse> inventories = inventoryService.findAll();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<?> getById(@PathVariable("inventoryId") Long id) {
        InventoryResponse inventoryResponse = inventoryService.findById(id);
        return new ResponseEntity<>(inventoryResponse, HttpStatus.OK);
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<?> updateById(@PathVariable("inventoryId") Long id,
                                        @RequestBody InventoryRequest inventoryRequest) {
        inventoryService.update(id, inventoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        inventoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
