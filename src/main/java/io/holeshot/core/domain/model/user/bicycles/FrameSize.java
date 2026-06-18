package io.holeshot.core.domain.model.user.bicycles;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Frame_Sizes")
@Data
public class FrameSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "frameSize")
    private List<Bicycle> bicycles;
}
