package ua.sych.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Contract extends idHolder {
    private String number;
    private String date;
    @OneToOne(mappedBy = "contract")
    private Partner partner;
}
