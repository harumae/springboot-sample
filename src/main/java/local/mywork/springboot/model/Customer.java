package local.mywork.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends AbstractEntity {
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
}
