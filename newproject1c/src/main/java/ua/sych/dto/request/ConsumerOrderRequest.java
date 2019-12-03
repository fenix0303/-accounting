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
public class ConsumerOrderRequest {
    @NotNull(message = "Field 'number' can not be null")
    private Integer number;
    @NotNull(message = "Field can not be null")
    @Size(min = 1, max = 20)
    private String date;
    @NotNull(message = "Field can not be null")
    private Long partnerId;
    private List<Long> productsId;
}
