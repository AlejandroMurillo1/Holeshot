package io.holeshot.core.infrastructure.web.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        @NotBlank String name,
        @NotBlank String lastName,
        @Email String email,
        @NotBlank String phoneNumber
) {}
