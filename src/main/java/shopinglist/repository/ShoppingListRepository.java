package shopinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopinglist.domain.ShoppingList;
import shopinglist.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    List<ShoppingList> findAllByUser(User user);

    Optional<ShoppingList> findByUserAndId(User user, Long id);
}