package io.holeshot.core.domain.model.user.bicycles;

import io.holeshot.core.domain.model.user.UserProfile;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Frame_Brands")
@Data
public class FrameBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "frameBrand")
    private List<Bicycle> bicycles;

}
