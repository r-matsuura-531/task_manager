package com.example.taskmanager.infrastructure.menu;


import lombok.Getter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import java.util.Date;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Getter
public class MenuEntity {

	/** ID */
	@Id
	private String id;

	/** 名前 */
	@Column(name = "name")
	private String name;

	/** URL */
	@Column(name = "url")
	private String url;

	/** 並び順 */
	@Column(name = "order_by")
	private Integer orderBy;

}