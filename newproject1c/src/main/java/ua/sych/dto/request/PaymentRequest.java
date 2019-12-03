package ua.sych.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
    @NotNull(message = "Field 'type' can not be null")
    @Size(min = 4, max = 100)
    private String type;
}
