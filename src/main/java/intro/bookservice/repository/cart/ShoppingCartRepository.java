package intro.bookservice.repository.cart;

import intro.bookservice.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query("FROM ShoppingCart sc LEFT JOIN FETCH sc.cartItems WHERE sc.id = :id")
    Optional<ShoppingCart> findShoppingCartByIdAndCartItems(Long id);
}
