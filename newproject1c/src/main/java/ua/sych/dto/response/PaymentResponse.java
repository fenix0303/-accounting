package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.sych.entity.Payment;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    private Long id;
    private String type;

    public PaymentResponse(Payment payment) {
        this.id = payment.getId();
        this.type = payment.getType();
    }
}
