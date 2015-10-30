package local.mywork.springboot.repository;

import local.mywork.springboot.model.Customer;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class CustomerSpecifications {
	public static Specification<Customer> containsFirstName(String firstName) {
		return Objects.isNull(firstName) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(root.get("firstName"), "%" + firstName + "%");
			}
		};
	}

	public static Specification<Customer> containsLastName(String lastName) {
		// lambda expression
		return Objects.isNull(lastName)
			? null
			: (root, cq, cb) -> cb.like(root.get("lastName"), "%" + lastName + "%");
	}
}
