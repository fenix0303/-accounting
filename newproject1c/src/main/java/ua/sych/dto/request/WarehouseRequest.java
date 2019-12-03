package ua.sych.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WarehouseRequest {
    @NotNull(message = "Field 'name' can not be null")
    @Size(min = 2, max = 20)
    private String name;
//    private List<Long> productsId;
}
