package ua.sych.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Inventory extends idHolder {
    @OneToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
    @OneToMany
    private List<Product> products = new ArrayList<>();
}
