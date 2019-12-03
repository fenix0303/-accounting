package ua.sych.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class EnterpriseRequest {
    @NotNull(message = "Field 'name' can not be null")
    @Size(min = 4, max = 100)
    private String name;
    @NotNull(message = "Field 'address' can not be null")
    @Size(min = 4, max = 100)
    private String address;
    @NotNull(message = "Field 'taxNumber' can not be null")
    @Size(min = 4, max = 100)
    private String taxNumber;
}
