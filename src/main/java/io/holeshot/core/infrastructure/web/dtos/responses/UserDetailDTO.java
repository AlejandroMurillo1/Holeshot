package io.holeshot.core.infrastructure.web.dtos.responses;

public record UserDetailDTO(
        Long id,
        String name,
        String lastName,
        String email,
        Integer age,
        String phoneNumber,
        String documentNumber
) {}
