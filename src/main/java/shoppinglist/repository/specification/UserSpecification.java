package shoppinglist.repository.specification;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import shoppinglist.dto.user.filter.UserFilterDto;
import shoppinglist.entity.Role;
import shoppinglist.entity.User;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class UserSpecification {

    @SuppressWarnings("unchecked")
    public static Specification<User> findUsers(Collection<UserFilterDto> filters) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            Fetch<User, Role> rolesFetch = root.fetch("roles", JoinType.LEFT);
            Join<User, Role> rolesJoin = (Join<User, Role>) rolesFetch;

            for (var filter : filters) {
                switch (filter.getUserField()) {
                    case ID -> {
                        Set<Long> ids = filter.getValues().stream().map(Long::parseLong).collect(Collectors.toSet());
                        predicates.add(criteriaBuilder.in(root.get("id")).value(ids));
                    }

                    case EMAIL -> predicates.add(criteriaBuilder.in(root.get("email")).value(filter.getValues()));

                    case ROLES -> {
                        predicates.add(criteriaBuilder.in(rolesJoin.get("code")).value(filter.getValues()));
                    }

                    default -> throw new IllegalArgumentException();
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}