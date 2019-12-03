package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.sych.entity.ConsumerOrder;
import ua.sych.utils.ObjectMapperUtils;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class ConsumerOrderResponse {
    private Long id;
    private Integer number;
    private String date;
    private Integer totalValue;
    private PartnerResponse partner;
    private List<ProductResponse> products;

    public ConsumerOrderResponse(ConsumerOrder order) {
        this.id = order.getId();
        this.number = order.getNumber();
        this.date = order.getDate();
        this.totalValue=order.getTotalValue();
        this.partner = ObjectMapperUtils.map(order.getPartner(), PartnerResponse.class);
        this.products = order.getProducts().stream()
                .map(ProductResponse::new).collect(Collectors.toList());
    }
}
