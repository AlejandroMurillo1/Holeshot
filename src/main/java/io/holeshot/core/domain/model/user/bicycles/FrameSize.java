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
@Table(name = "frame_sizes")
@Data
public class FrameSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @OneToMany(mappedBy = "frameSize")
    private List<Bicycle> bicycles;
}
