package io.holeshot.core.infrastructure.web.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateAthleteDTO(
        @PositiveOrZero @NotNull Long userId,
        @PositiveOrZero Long uciCategoryId,
        String photoUrl,
        String documentUrl
) {
}
