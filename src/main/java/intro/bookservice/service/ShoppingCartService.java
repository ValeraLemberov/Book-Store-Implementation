package intro.bookservice.service;

import intro.bookservice.dto.cart_item.CartItemRequestDto;
import intro.bookservice.dto.cart_item.CartItemResponseDto;
import intro.bookservice.dto.cart_item.QuantityRequestDto;
import intro.bookservice.dto.shopping_cart.ShoppingCartResponseDto;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    ShoppingCartResponseDto getShoppingCart(Authentication authentication);

    CartItemResponseDto addBook(Authentication authentication, CartItemRequestDto requestDto);

    CartItemResponseDto updateQuantity(Long cartId, QuantityRequestDto requestDto);

    void deleteCartItem(Long id);
}
