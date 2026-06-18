package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "Monthly_Payments")
@Data
@EqualsAndHashCode(callSuper = true)
public class MonthlyPayment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;
    private LocalDate date;
    private String voucherUrl;

    @ManyToOne @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne @JoinColumn(name = "payment_status_id")
    private PaymentStatus paymentStatus;
}
