package intro.bookservice.service;

import intro.bookservice.dto.order.ChangeStatusDto;
import intro.bookservice.dto.order.CreateOrderDto;
import intro.bookservice.dto.order.OrderResponseDto;
import intro.bookservice.dto.order_item.OrderItemResponseDto;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.Authentication;

public interface OrderService {

    OrderResponseDto createOrder(Authentication authentication,
                                 CreateOrderDto createOrderDto);

    List<OrderResponseDto> getOrdersHistory(Authentication authentication);

    OrderResponseDto changeOrderStatus(Long orderId, ChangeStatusDto changeStatusDto);

    Set<OrderItemResponseDto> getAllOrderItems(Authentication authentication, Long orderId);

    OrderItemResponseDto findSpecificOrderItem(Authentication authentication, Long orderId, Long orderItemId);
}
