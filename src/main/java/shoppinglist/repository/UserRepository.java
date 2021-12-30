package shoppinglist.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shoppinglist.entity.User;
import shoppinglist.entity.projection.UserIdProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    UserIdProjection findOneByEmail(String email);

    @EntityGraph("User.roles")
    @Query("select u from User u")
    List<User> findAllWithRoles();

    @EntityGraph("User.roles")
    Optional<User> findOneWithRolesByEmail(String email);
}