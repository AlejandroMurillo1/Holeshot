package io.holeshot.core.infrastructure.web.dtos.responses;

public record ClubDetailDTO(
        String name,
        Double monthlyPayment,
        String city,
        String email,
        String phone,
        String address
        //TODO: Campos faltantes con sus DTOs
) {
}
