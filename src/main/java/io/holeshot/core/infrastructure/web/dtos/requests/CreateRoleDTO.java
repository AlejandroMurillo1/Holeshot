package io.holeshot.core.infrastructure.web.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleDTO(
        @NotBlank String name,
        String description
) {
}
