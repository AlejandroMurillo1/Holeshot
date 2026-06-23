package io.holeshot.core.infrastructure.web.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record CreatePermissionDTO(
        @NotBlank String name,
        String description
) {
}
