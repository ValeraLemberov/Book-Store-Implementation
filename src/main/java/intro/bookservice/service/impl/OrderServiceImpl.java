package intro.bookservice.service.impl;

import intro.bookservice.dto.order.ChangeStatusDto;
import intro.bookservice.dto.order.CreateOrderDto;
import intro.bookservice.dto.order.OrderResponseDto;
import intro.bookservice.dto.order_item.OrderItemResponseDto;
import intro.bookservice.mapper.OrderItemMapper;
import intro.bookservice.mapper.OrderMapper;
import intro.bookservice.model.CartItem;
import intro.bookservice.model.Order;
import intro.bookservice.model.OrderItem;
import intro.bookservice.model.User;
import intro.bookservice.model.enums.Status;
import intro.bookservice.repository.order_item.OrderItemRepository;
import intro.bookservice.repository.order.OrderRepository;
import intro.bookservice.service.OrderService;
import intro.bookservice.service.ShoppingCartService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartService shoppingCartService;

    @Override
    public OrderResponseDto createOrder(Authentication authentication,
                                        CreateOrderDto createOrderDto) {
        User user = (User) authentication.getPrincipal();
        Order order = orderMapper.toModel(createOrderDto);
        order.setUser(user);
        order.setStatus(Status.CREATED);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
        for(CartItem cartItem: user.getShoppingCart().getCartItems()) {
            OrderItem orderItem = orderItemMapper.toOrderItem(cartItem);
            orderItem.setOrder(order);
            order.setTotal(order.getTotal().add(orderItem.getPrice()
                    .multiply(BigDecimal.valueOf(orderItem.getQuantity()))));
            orderItemRepository.save(orderItem);
            order.getOrderItems().add(orderItem);
            shoppingCartService.deleteCartItem(cartItem.getId());
        }
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderResponseDto> getOrdersHistory(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderRepository.getAllByUserId(user.getId()).stream()
                .map(orderMapper::toDto).toList();
    }

    @Override
    public OrderResponseDto changeOrderStatus(Long orderId, ChangeStatusDto changeStatusDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Can't find a order by id: " + orderId));
        order.setStatus(changeStatusDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public Set<OrderItemResponseDto> getAllOrderItems(Authentication authentication, Long orderId) {
        User user = (User) authentication.getPrincipal();
        return orderItemRepository.getAllByOrderId(orderId, user.getId()).stream()
                .map(orderItemMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public OrderItemResponseDto findSpecificOrderItem(Authentication authentication,
                                                     Long orderId,
                                                     Long orderItemId) {
        User user = (User) authentication.getPrincipal();
        return orderItemMapper.toDto(orderItemRepository
                .findByIdAndOrderId(user.getId(), orderId, orderItemId)
                .orElseThrow(() -> new NoSuchElementException("Can't find a order item by id: "
                        + orderItemId)));
    }
}
