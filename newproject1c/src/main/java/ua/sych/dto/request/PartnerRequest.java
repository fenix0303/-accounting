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
public class PartnerRequest {
    @NotNull(message = "Field 'name' can not be null")
    @Size(min = 4, max = 50)
    private String name;
    @NotNull(message = "Field 'address' can not be null")
    @Size(min = 4, max = 100)
    private String address;
    @NotNull(message = "Field 'enterprise' can not be null")
    private Long enterpriseId;
    private List<Long> paymentId;

}
