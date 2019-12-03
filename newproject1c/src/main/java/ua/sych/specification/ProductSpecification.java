package ua.sych.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.sych.dto.request.FilterProductRequest;
import ua.sych.dto.request.OneFilterProductRequest;
import ua.sych.entity.Product;
import ua.sych.entity.Warehouse;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private FilterProductRequest filterProductRequest;

    public ProductSpecification(FilterProductRequest filterProductRequest) {
        this.filterProductRequest = filterProductRequest;
    }

    private Predicate filterByWarehouse(Root<Product> root,
                                        CriteriaBuilder criteriaBuilder, OneFilterProductRequest oneFilterProductRequest) {
        Join<Product, Warehouse> joinProductToWarehouse = root.join("warehouse");
        return criteriaBuilder.equal(joinProductToWarehouse.get("name"), oneFilterProductRequest.getFirstValue());
    }

    private Predicate filterByName(Root<Product> root, CriteriaBuilder criteriaBuilder,
                                   OneFilterProductRequest oneFilterProductRequest) {
        return criteriaBuilder.like(root.get("name"), "%" + oneFilterProductRequest.getFirstValue() + "%");
    }

    private Predicate filterByPriceForSale(Root<Product> root, CriteriaBuilder criteriaBuilder,
                                           OneFilterProductRequest oneFilterProductRequest) {
        if (oneFilterProductRequest.getFirstValue() != null && oneFilterProductRequest.getSecondValue() != null) {
            return criteriaBuilder.between(root.get("priceForSale"),
                    Integer.parseInt(oneFilterProductRequest.getFirstValue()), Integer.parseInt(oneFilterProductRequest.getSecondValue()));
        } else {
            return criteriaBuilder.conjunction();
        }
    }

    private Predicate filterByPrice(Root<Product> root, CriteriaBuilder criteriaBuilder, OneFilterProductRequest oneFilterProductRequest) {
        if (oneFilterProductRequest.getFirstValue() != null) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), oneFilterProductRequest.getFirstValue());
        } else {
            return criteriaBuilder.conjunction();
        }
    }


    private Predicate createFilter(Root<Product> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        filterProductRequest.getOneFilterProductRequests().forEach(oneFilterProductRequest -> {
            switch (oneFilterProductRequest.getCriteriaForSearchProduct()) {
                case BY_WAREHOUSE: {
                    predicates.add(filterByWarehouse(root, criteriaBuilder, oneFilterProductRequest));
                    break;
                }
                case BY_NAME_LIKE: {
                    predicates.add(filterByName(root, criteriaBuilder, oneFilterProductRequest));
                    break;
                }
                case BY_PRICE_FOR_SALE_BETWEEN: {
                    predicates.add(filterByPriceForSale(root, criteriaBuilder, oneFilterProductRequest));
                    break;
                }
                case BY_PRICE_FOR_SALE_LESS_THAN: {
                    predicates.add(filterByPrice(root, criteriaBuilder, oneFilterProductRequest));
                    break;
                }
            }
        });
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return createFilter(root, criteriaBuilder);
    }
}
