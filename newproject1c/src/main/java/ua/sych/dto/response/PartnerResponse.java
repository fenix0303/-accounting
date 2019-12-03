package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.sych.entity.Partner;
import ua.sych.utils.ObjectMapperUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PartnerResponse {
    private Long id;
    private String name;
    private String address;
    private EnterpriseResponse enterprise;
    private ContractResponse contract;

    public PartnerResponse(Partner partner) {
        this.id = partner.getId();
        this.name = partner.getName();
        this.address = partner.getAddress();
        this.enterprise = ObjectMapperUtils.map(partner.getEnterprise(), EnterpriseResponse.class);
        this.contract = ObjectMapperUtils.map(partner.getContract(), ContractResponse.class);
    }
}
