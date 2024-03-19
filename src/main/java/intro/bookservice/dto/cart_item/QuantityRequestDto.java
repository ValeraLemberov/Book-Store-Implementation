package intro.bookservice.dto.cart_item;

import jakarta.validation.constraints.NotNull;

public record QuantityRequestDto(
        @NotNull
        Integer quantity
) {
}
