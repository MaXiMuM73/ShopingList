package shoppinglist.repository;

import shoppinglist.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Set<Role> findAllByCodeIn(Collection<String> codes);
}