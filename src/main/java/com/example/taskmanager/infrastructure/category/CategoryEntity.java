package com.example.taskmanager.infrastructure.category;


import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Getter
@Setter
@Table(name = "category")
public class CategoryEntity {

	/** ID */
	@Id
	private String id;

	/** カテゴリ名 */
	@Column(name = "name")
	private String name;

}
