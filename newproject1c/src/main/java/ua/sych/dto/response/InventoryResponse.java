package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.sych.entity.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class InventoryResponse {
    private Long id;
    private String warehouse;
    private List<ProductResponse> products = new ArrayList<>();

    public InventoryResponse(Inventory inventory) {
        this.id = inventory.getId();
        this.warehouse = inventory.getWarehouse().getName();
        this.products =inventory.getProducts().stream().
                map(ProductResponse::new).collect(Collectors.toList());
    }
}
