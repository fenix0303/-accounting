package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.WarehouseRequest;
import ua.sych.dto.response.WarehouseResponse;
import ua.sych.entity.Warehouse;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.WarehouseRepository;
import ua.sych.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class WarehouseServiceImpl {
    @Autowired
    private WarehouseRepository warehouseRepository;

     public void save(WarehouseRequest warehouseRequest) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseRequest.getName());
        warehouseRepository.save(warehouse);
    }

    public List<WarehouseResponse> findAll() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return ObjectMapperUtils.mapAll(warehouses, WarehouseResponse.class);
    }

    public WarehouseResponse findById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Warehouse with id ["
                        + id + "] not found"));
        return ObjectMapperUtils.map(warehouse, WarehouseResponse.class);
    }

      public void update(Long id, WarehouseRequest warehouseRequest) {
        Warehouse warehouseByDB = warehouseRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Warehouse with "
                        + id + " not found"));
        warehouseByDB.setName(warehouseRequest.getName());
        warehouseRepository.save(warehouseByDB);
    }

      public void delete(Long id) {
        Warehouse warehouseByDB = warehouseRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Warehouse with "
                        + id + " not found"));
        if (warehouseByDB.getProducts().isEmpty()) {
            warehouseRepository.delete(warehouseByDB);
        } else {
            throw new WrongInputDataException("Warehouse with "
                    + id + " has products");
        }
    }
}
