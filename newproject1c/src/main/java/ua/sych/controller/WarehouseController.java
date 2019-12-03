package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.WarehouseRequest;
import ua.sych.dto.response.WarehouseResponse;
import ua.sych.service.serviceimpl.WarehouseServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseServiceImpl warehouseService;

    @PostMapping
    public ResponseEntity<?> createWarehouse(@RequestBody
                                                 @Valid WarehouseRequest warehouse) {
        warehouseService.save(warehouse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<WarehouseResponse> warehouses = warehouseService.findAll();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}")
    public ResponseEntity<?> getWarehouseById(@PathVariable("warehouseId") Long id) {
        WarehouseResponse warehouseResponse = warehouseService.findById(id);
        return new ResponseEntity<>(warehouseResponse, HttpStatus.OK);
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity<?> updateUserById(@PathVariable("warehouseId") Long id,
                                            @RequestBody WarehouseRequest warehouseRequest) {
        warehouseService.update(id, warehouseRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        warehouseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
