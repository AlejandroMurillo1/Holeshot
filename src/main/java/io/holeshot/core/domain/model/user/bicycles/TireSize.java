package io.holeshot.core.domain.model.user.bicycles;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Tire_Sizes")
@Data
public class TireSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;

    @OneToMany(mappedBy = "tireSize")
    private List<Bicycle> bicycles;
}
