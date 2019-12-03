package ua.sych.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Enterprise extends idHolder{
    private String name;
    private String address;
    private String taxNumber;
    @OneToMany(mappedBy = "enterprise")
    private List<Partner> partner = new ArrayList<>();
}
