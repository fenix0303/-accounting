package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sych.dto.request.FilterProductRequest;
import ua.sych.dto.request.ProductRequest;
import ua.sych.dto.response.DataResponse;
import ua.sych.dto.response.ProductResponse;
import ua.sych.service.serviceimpl.ProductService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        productService.saveProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public DataResponse<ProductResponse> getAll(@RequestParam Integer page, @RequestParam Integer size,
                                                @RequestParam String sortBy, @RequestParam Sort.Direction direction,
                                                @RequestParam(required = false) String name) {
        return productService.getAll(page, size, sortBy, direction, name);
    }

    @PostMapping("/filter")
    public List<ProductResponse> filter(@RequestBody FilterProductRequest filterProductRequest) {
        return productService.filter(filterProductRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<ProductResponse> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> update(@PathVariable("productId") Long id,
                                    @RequestBody ProductRequest product) {
        productService.update(id, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
