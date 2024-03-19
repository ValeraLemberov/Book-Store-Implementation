package intro.bookservice.controller;

import intro.bookservice.dto.cart_item.CartItemRequestDto;
import intro.bookservice.dto.cart_item.CartItemResponseDto;
import intro.bookservice.dto.cart_item.QuantityRequestDto;
import intro.bookservice.dto.shopping_cart.ShoppingCartResponseDto;
import intro.bookservice.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart", description = "Endpoints for shopping cart management.")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Get shopping cart", description = "Get shopping cart with all cart items.")
    @GetMapping
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        return shoppingCartService.getShoppingCart(authentication);
    }

    @Operation(summary = "Add a book item.", description = "Add a book to the shopping cart.")
    @PostMapping
    public CartItemResponseDto addBook(
            Authentication authentication,
            @RequestBody CartItemRequestDto cartDto) {
        return shoppingCartService.addBook(authentication, cartDto);
    }

    @Operation(summary = "Change quantity.", description = "Change quantity in the specific cart item.")
    @PutMapping("/cart-items/{cartItemId}")
    public CartItemResponseDto updateQuantity(
            @PathVariable Long cartItemId,
            @RequestBody QuantityRequestDto requestDto) {
        return shoppingCartService.updateQuantity(cartItemId, requestDto);
    }

    @Operation(summary = "Delete a cart item.",description = "Delete a cart item from the shopping cart.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{cartItemId}")
    public void deleteCartItem(@PathVariable Long cartItemId) {
        shoppingCartService.deleteCartItem(cartItemId);
    }


}
