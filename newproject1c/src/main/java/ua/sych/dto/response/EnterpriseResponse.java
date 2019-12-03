package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ua.sych.entity.Enterprise;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class EnterpriseResponse {
    private Long id;
    private String name;
    private String address;
    private String taxNumber;


    public EnterpriseResponse(Enterprise enterprise) {
        this.id = enterprise.getId();
        this.name = enterprise.getName();
        this.address = enterprise.getAddress();
        this.taxNumber = enterprise.getTaxNumber();
    }
}
