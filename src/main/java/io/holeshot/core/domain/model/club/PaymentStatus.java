package io.holeshot.core.domain.model.club;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Payment_Statuses")
@Data
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "paymentStatus")
    private List<MonthlyPayment> payments;
}
