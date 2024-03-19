package intro.bookservice.mapper;

import intro.bookservice.config.MapperConfig;
import intro.bookservice.dto.order.CreateOrderDto;
import intro.bookservice.dto.order.OrderResponseDto;
import intro.bookservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {

    Order toModel(CreateOrderDto createOrderDto);

    @Mapping(target = "orderItems", source = "orderItems", qualifiedByName = "makeDtoList")
    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);
}
