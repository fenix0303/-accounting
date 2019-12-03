package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.sych.entity.Product;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Integer priceForSale;
    private Integer purchasePrice;
    private Integer amount;
    private String warehouse;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.priceForSale=product.getPriceForSale();
        this.purchasePrice=product.getPurchasePrice();
        this.amount=product.getAmount();
        this.warehouse = product.getWarehouse().getName();
    }
}
