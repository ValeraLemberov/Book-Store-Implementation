package intro.bookservice.dto.order;

import intro.bookservice.model.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeStatusDto {
    @NotNull
    private Status status;
}
