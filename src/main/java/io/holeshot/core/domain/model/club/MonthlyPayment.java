package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true, exclude = {"enrollment", "paymentMethod", "paymentStatus"})
@ToString(exclude = {"enrollment", "paymentMethod", "paymentStatus"})
@Entity
@Table(name = "monthly_payments")
@Data
public class MonthlyPayment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "value", nullable = false, precision = 10, scale = 2)
    private Double value;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Size(max = 500)
    @Column(name = "voucher_url", length = 500)
    private String voucherUrl;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "payment_status_id", nullable = false)
    private PaymentStatus paymentStatus;
}
