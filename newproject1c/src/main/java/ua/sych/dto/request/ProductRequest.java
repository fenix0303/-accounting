package ua.sych.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
    private String name;
    @NotNull(message = "Field 'priceForSale' can not be null")
    private Integer priceForSale;
    @NotNull(message = "Field 'purchasePrice' can not be null")
    private Integer purchasePrice;
    @NotNull(message = "Field 'amount' can not be null")
    private Integer amount;
    private Long warehouseId;
 }
