package io.holeshot.core.infrastructure.web.dtos.requests;

import jakarta.validation.constraints.*;

public record CreateClubDTO(
        @NotBlank String name,
        @NotNull @PositiveOrZero Double monthlyPayment,
        @NotBlank String city,
        @Email String email,
        @NotBlank String address,
        @NotBlank String phone,
        @NotNull Boolean isDepartmentTeam
) {
}
