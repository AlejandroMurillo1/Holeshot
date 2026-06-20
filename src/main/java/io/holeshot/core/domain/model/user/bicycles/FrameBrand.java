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
@Table(name = "frame_brands")
@Data
public class FrameBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "name", nullable = false, unique = true, length = 80)
    private String name;

    @OneToMany(mappedBy = "frameBrand")
    private List<Bicycle> bicycles;
}
