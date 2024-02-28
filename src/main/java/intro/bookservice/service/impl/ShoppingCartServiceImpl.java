package intro.bookservice.service.impl;

import intro.bookservice.dto.cart_item.CartItemRequestDto;
import intro.bookservice.dto.cart_item.CartItemResponseDto;
import intro.bookservice.dto.cart_item.QuantityRequestDto;
import intro.bookservice.dto.shopping_cart.ShoppingCartResponseDto;
import intro.bookservice.mapper.CartItemMapper;
import intro.bookservice.mapper.ShoppingCartMapper;
import intro.bookservice.model.CartItem;
import intro.bookservice.model.ShoppingCart;
import intro.bookservice.model.User;
import intro.bookservice.repository.book.BookRepository;
import intro.bookservice.repository.cart.CartItemRepository;
import intro.bookservice.repository.cart.ShoppingCartRepository;
import intro.bookservice.service.ShoppingCartService;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;


    @Override
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        return shoppingCartMapper.toDto(findShoppingCart(authentication));
    }

    @Override
    public CartItemResponseDto addBook(Authentication authentication, CartItemRequestDto requestDto) {
        ShoppingCart shoppingCart = findShoppingCart(authentication);
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.setBook(bookRepository.findById(cartItem.getBook().getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find a book with id: "
                        + cartItem.getBook().getId())));
        shoppingCart.getCartItems().add(cartItem);
        cartItem.setShoppingCart(shoppingCart);
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public CartItemResponseDto updateQuantity(Long cartId,
                                             QuantityRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findById(cartId)
                .orElseThrow(() -> new NoSuchElementException("Can't find a cart item by id: "
                        + cartId));
        cartItem.setQuantity(requestDto.quantity());
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);

    }

    private ShoppingCart findShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<ShoppingCart> shoppingCartById
                = shoppingCartRepository.findShoppingCartByIdAndCartItems(user.getId());
        return shoppingCartById.orElseThrow(() ->
                new NoSuchElementException("Can't find shopping cart for user: "
                        + user.getEmail()));
    }
}
