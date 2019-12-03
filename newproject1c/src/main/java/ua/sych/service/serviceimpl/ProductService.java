package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.FilterProductRequest;
import ua.sych.dto.request.ProductRequest;
import ua.sych.dto.response.DataResponse;
import ua.sych.dto.response.ProductResponse;
import ua.sych.entity.Product;
import ua.sych.entity.Warehouse;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.ProductRepository;
import ua.sych.specification.ProductSpecification;
import ua.sych.utils.ObjectMapperUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseServiceImpl warehouseService;

    public void saveProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPriceForSale(productRequest.getPriceForSale());
        product.setPurchasePrice(productRequest.getPurchasePrice());
        product.setAmount(productRequest.getAmount());
        Warehouse warehouse = ObjectMapperUtils.map(warehouseService
                .findById(productRequest.getWarehouseId()), Warehouse.class);
        product.setWarehouse(warehouse);
        productRepository.save(product);
    }

    public DataResponse<ProductResponse> getAll(Integer page, Integer size,
                                                String sortBy, Sort.Direction direction,
                                                String name) {
        Sort sort = Sort.by(direction, sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Product> productPage;
        if (name != null) {
            productPage = productRepository.findAllByNameLike("%" + name + "%", pageRequest);
        } else {
            productPage = productRepository.findAll(pageRequest);
        }
        return new DataResponse<>(productPage.getContent().stream()
                .map(ProductResponse::new).collect(Collectors.toList()), productPage);
    }


    public List<ProductResponse> filter(FilterProductRequest filterProductRequest) {
        ProductSpecification productSpecification = new ProductSpecification(filterProductRequest);
        return productRepository.findAll(productSpecification).stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Product with id [" + id + "] not found"));

        return ObjectMapperUtils.map(product, ProductResponse.class);
    }

    public void update(Long id, ProductRequest productRequest) {
        Product productByDB = productRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Product with "
                        + id + " not found"));
        productByDB.setName(productRequest.getName());
        productByDB.setPurchasePrice(productRequest.getPurchasePrice());
        productByDB.setPriceForSale(productRequest.getPriceForSale());
        productByDB.setAmount(productRequest.getAmount());
        Warehouse warehouse = ObjectMapperUtils.map(warehouseService
                .findById(productRequest.getWarehouseId()), Warehouse.class);
        productByDB.setWarehouse(warehouse);
        productRepository.save(productByDB);
    }

    public void delete(Long id) {
        Product productByDB = productRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Product with "
                        + id + " not found"));
        productRepository.delete(productByDB);
    }

    public List<ProductResponse> findAll() {
        return ObjectMapperUtils.mapAll(productRepository.findAll(),
                ProductResponse.class);
    }
}

