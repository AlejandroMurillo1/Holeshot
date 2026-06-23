package io.holeshot.core.infrastructure.web.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateUserDTO(
        @NotBlank String name,
        @NotBlank String lastName,
        @Email String email,
        @NotBlank String password,
        @NotNull LocalDate birthDate,
        @NotBlank String phoneNumber,
        @NotBlank String documentNumber
) {}
