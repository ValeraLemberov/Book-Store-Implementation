package intro.bookservice.dto.cart_item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemRequestDto(
        @NotNull
        @Min(1)
        Long bookId,

        @NotNull
        @Min(1)
        Integer quantity
) {
}
