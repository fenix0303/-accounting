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
public class Partner extends idHolder {
    private String name;
    private String address;
    @ManyToOne
    private Enterprise enterprise;
    @OneToOne
    private Contract contract;
    @OneToMany(mappedBy = "partner")
    private List<ConsumerOrder> consumerOrders = new ArrayList<>();
    @OneToMany(mappedBy = "partner")
    private List<ProviderOrder> providerOrders = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "partner_payment", joinColumns = @JoinColumn(name = "partner_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private List<Payment> payments = new ArrayList<>();
}
