package io.holeshot.core.infrastructure.web.dtos.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;

public record UpdateClubDTO(
        String name,
        String logoUrl,
        @Positive Double monthlyPayment,
        String city,
        @Email String email,
        String address,
        String phone
) {
}
