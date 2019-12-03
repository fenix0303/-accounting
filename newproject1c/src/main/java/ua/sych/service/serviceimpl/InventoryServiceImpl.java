package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.InventoryRequest;
import ua.sych.dto.response.InventoryResponse;
import ua.sych.entity.Inventory;
import ua.sych.entity.Product;
import ua.sych.entity.Warehouse;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.InventoryRepository;
import ua.sych.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl {
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private WarehouseServiceImpl warehouseService;

    public void save(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        Warehouse warehouse = ObjectMapperUtils.map(warehouseService
                .findById(inventoryRequest.getWarehouseId()), Warehouse.class);
        List<Product> products = new ArrayList<>();
        for (Long id : inventoryRequest.getProductsId()
        ) {
            products.add(ObjectMapperUtils.map(productService.findById(id), Product.class));
        }
        inventory.setWarehouse(warehouse);
        inventory.setProducts(products);
        inventoryRepository.save(inventory);
    }

       public List<InventoryResponse> findAll() {

        return ObjectMapperUtils.mapAll(inventoryRepository.findAll(),
                InventoryResponse.class);
    }

    public InventoryResponse findById(Long id) {
        Inventory inventoryByDb = inventoryRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Inventory with "
                        + id + " not found"));
        return ObjectMapperUtils.map(inventoryByDb, InventoryResponse.class);
    }

    public void update(Long id, InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Inventory with "
                        + id + " not found"));
        Warehouse warehouse = ObjectMapperUtils.map(warehouseService
                .findById(inventoryRequest.getWarehouseId()), Warehouse.class);
        List<Product> products = new ArrayList<>();
        for (Long idProduct : inventoryRequest.getProductsId()
        ) {
            products.add(ObjectMapperUtils.map(productService.findById(idProduct),
                    Product.class));
        }
        inventory.setWarehouse(warehouse);
        inventory.setProducts(products);
        inventoryRepository.save(inventory);
    }

    public void delete(Long id) {
        Inventory inventoryByDb = inventoryRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Inventory with "
                        + id + " not found"));
        if (inventoryByDb.getProducts().isEmpty()) {
            inventoryRepository.delete(inventoryByDb);
        } else {
            throw new WrongInputDataException("Inventory with "
                    + id + " has products");
        }
    }
}
