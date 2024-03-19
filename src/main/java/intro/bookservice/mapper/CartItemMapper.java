package intro.bookservice.mapper;

import intro.bookservice.config.MapperConfig;
import intro.bookservice.dto.cart_item.CartItemRequestDto;
import intro.bookservice.dto.cart_item.CartItemResponseDto;
import intro.bookservice.model.CartItem;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface CartItemMapper {

    @Mapping(target = "book", source = "bookId", qualifiedByName = "bookFromId")
    CartItem toModel(CartItemRequestDto requestDto);

    @Mapping(target = "bookId", ignore = true)
    @Mapping(target = "bookTitle", ignore = true)
    CartItemResponseDto toDto(CartItem cartItem);

    @AfterMapping
    default void setBookAttributes(
            @MappingTarget CartItemResponseDto responseDto,
            CartItem cartItem) {
        responseDto.setBookId(cartItem.getBook().getId());
        responseDto.setBookTitle(cartItem.getBook().getTitle());
    }

    @Named("getCartItemsDto")
    default Set<CartItemResponseDto> getCartItemsDto(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }
}
