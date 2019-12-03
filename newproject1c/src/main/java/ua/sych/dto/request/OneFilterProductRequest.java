package ua.sych.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OneFilterProductRequest {

    private CriteriaForSearchProduct criteriaForSearchProduct;

    private String firstValue;

    private String secondValue;




}
