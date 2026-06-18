package io.holeshot.core.domain.model.user.bicycles;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Tire_Brands")
@Data
public class TireBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tireBrand")
    private List<Bicycle> bicycles;
}
