package intro.bookservice.mapper;

import intro.bookservice.config.MapperConfig;
import intro.bookservice.dto.shopping_cart.ShoppingCartResponseDto;
import intro.bookservice.model.ShoppingCart;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "cartItems", source = "cartItems", qualifiedByName = "getCartItemsDto")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);

    @AfterMapping
    default void getUserId(
            @MappingTarget ShoppingCartResponseDto cartDto,
            ShoppingCart shoppingCart) {
        cartDto.setUserId(shoppingCart.getUser().getId());
    }
}
