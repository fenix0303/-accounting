package ua.sych.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConsumerOrder extends idHolder {
    private Integer number;
    private Integer totalValue;
    private String date;
    @ManyToOne
    private Partner partner;
    @OneToMany
    private List<Product> products = new ArrayList<>();
}
