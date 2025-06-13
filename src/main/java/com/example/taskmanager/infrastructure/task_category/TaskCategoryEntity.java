package com.example.taskmanager.infrastructure.task_category;

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
@Table(name = "task_category")
public class TaskCategoryEntity {

	/** ID */
	@Id
	private String id;

	/** カテゴリID */
	@Column(name = "category_id")
	private String categoryId;

	/** タスクID */
	@Column(name = "task_id")
	private String taskId;
}
