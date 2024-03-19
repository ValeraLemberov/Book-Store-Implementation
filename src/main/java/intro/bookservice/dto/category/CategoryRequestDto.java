package intro.bookservice.dto.category;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
        @NotNull
        String name,
        String description
) {
}
