package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.sych.entity.Warehouse;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class WarehouseResponse {
    private Long id;
    private String name;
    private List<ProductResponse> products = new ArrayList<>();

    public WarehouseResponse(Warehouse warehouse) {
        this.id = warehouse.getId();
        this.name = warehouse.getName();
        this.products = warehouse.getProducts().stream()
                .map(ProductResponse::new).collect(Collectors.toList());
    }
}
