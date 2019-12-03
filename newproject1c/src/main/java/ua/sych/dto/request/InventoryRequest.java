package ua.sych.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InventoryRequest {
    @NotNull(message = "Field can not be null")
    private Long warehouseId;
    @NotNull(message = "Field can not be null")
    private List<Long> productsId;
}
