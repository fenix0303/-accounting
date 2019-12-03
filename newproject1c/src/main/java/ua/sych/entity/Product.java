package ua.sych.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product extends idHolder {
    private String name;
    private Integer priceForSale;
    private Integer purchasePrice;
    private Integer amount;
    @ManyToOne
    private Warehouse warehouse;
   }
