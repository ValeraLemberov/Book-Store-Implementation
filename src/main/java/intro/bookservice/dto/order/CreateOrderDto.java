package intro.bookservice.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderDto {

    @NotNull
    private String shippingAddress;
}
