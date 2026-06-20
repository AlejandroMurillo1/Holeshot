package io.holeshot.core.domain.model.user.bicycles;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(exclude = "bicycles")
@ToString(exclude = "bicycles")
@Entity
@Table(name = "tire_sizes")
@Data
public class TireSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "size", nullable = false, unique = true, length = 50)
    private String size;

    @OneToMany(mappedBy = "tireSize")
    private List<Bicycle> bicycles;
}
