package io.holeshot.core.infrastructure.web.dtos.requests;

import jakarta.validation.constraints.Positive;

public record CreateBicycleDTO(
        @Positive Double cranksInches,
        @Positive Double gear,
        @Positive Integer rearGear,
        @Positive Double handlebarInches,
        @Positive Integer frameId,
        @Positive Integer frameSizeId,
        @Positive Integer tireBrandId,
        @Positive Integer tireSizeId
) {}
