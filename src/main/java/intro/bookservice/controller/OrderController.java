package intro.bookservice.controller;

import intro.bookservice.dto.order.ChangeStatusDto;
import intro.bookservice.dto.order.CreateOrderDto;
import intro.bookservice.dto.order.OrderResponseDto;
import intro.bookservice.dto.order_item.OrderItemResponseDto;
import intro.bookservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Endpoints for order management.")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Create order.", description = "Create a new order based on a shopping cart.")
    @PostMapping
    public OrderResponseDto createOrder(Authentication authentication,
                                        @RequestBody CreateOrderDto createOrderDto) {
        return orderService.createOrder(authentication, createOrderDto);
    }

    @Operation(summary = "Get orders history.", description = "Get all your orders.")
    @GetMapping
    public List<OrderResponseDto> getOrdersHistory(Authentication authentication) {
        return orderService.getOrdersHistory(authentication);
    }

    @Operation(summary = "Change status.", description = "Change order status, available only for admin.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{orderId}")
    public OrderResponseDto changeStatus(@PathVariable Long orderId,
                               @RequestBody ChangeStatusDto changeStatusDto) {
        return orderService.changeOrderStatus(orderId, changeStatusDto);
    }

    @Operation(summary = "Get all order items", description = "Get all order items in order by order id.")
    @GetMapping("/{orderId}/items")
    public Set<OrderItemResponseDto> getAllOrderItems(Authentication authentication,
                                                      @PathVariable Long orderId) {
        return orderService.getAllOrderItems(authentication, orderId);
    }

    @Operation(summary = "Get order item.", description = "Get specific order item by order id and order item id.")
    @GetMapping("/{orderId}/items/{itemId}")
    public Object getOrderItem(Authentication authentication,
                               @PathVariable Long orderId,
                               @PathVariable Long itemId) {
        return orderService.findSpecificOrderItem(authentication, orderId, itemId);
    }
}
