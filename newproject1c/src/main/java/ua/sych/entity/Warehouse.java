package ua.sych.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Warehouse extends idHolder {
    private String name;
    @OneToMany(mappedBy = "warehouse")
    private List<Product> products = new ArrayList<>();

}
