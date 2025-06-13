package com.example.taskmanager.infrastructure.task;


import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import java.util.Date;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Getter
@Setter
@Table(name = "task")
public class TaskEntity {

	/** ID */
	@Id
	private String id;

	/** タイトル */
	@Column(name = "title")
	private String title;

	/** 詳細 */
	@Column(name = "description")
	private String description;

	/** 期限 */
	@Column(name = "due_date")
	private Date dueDate;

	/** ステータス */
	@Column(name = "status")
	private Integer status;
}