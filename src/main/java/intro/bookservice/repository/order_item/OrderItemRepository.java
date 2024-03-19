package intro.bookservice.repository.order_item;

import intro.bookservice.model.OrderItem;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("FROM OrderItem o "
            + "LEFT JOIN FETCH o.order oo "
            + "WHERE o.order.id = :orderId "
            + "AND oo.user.id = :userId")
    Set<OrderItem> getAllByOrderId(Long orderId, Long userId);

    @Query("FROM OrderItem o "
            + "LEFT JOIN FETCH o.order oo "
            + "WHERE o.id = :orderItemId "
            + "AND oo.id = :orderId "
            + "AND oo.user.id = :userId")
    Optional<OrderItem> findByIdAndOrderId(Long userId, Long orderId, Long orderItemId);
}
