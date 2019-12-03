package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.sych.entity.Contract;
import ua.sych.utils.ObjectMapperUtils;

@Getter
@Setter
@NoArgsConstructor
public class ContractResponse {
    private Long id;
    private String number;
    private String date;
    private String partner;

    public ContractResponse(Contract contract) {
        this.id = contract.getId();
        this.number = contract.getNumber();
        this.date = contract.getDate();
        this.partner = contract.getPartner().getName();
    }
}
