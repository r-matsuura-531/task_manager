package com.example.taskmanager.application.category.search;

import ch.qos.logback.core.util.StringUtil;
import com.example.taskmanager.domain.Order;
import com.example.taskmanager.domain.category.CategorySort;
import com.example.taskmanager.domain.task.TaskSort;
import com.example.taskmanager.domain.task.TaskStatus;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class SearchCategoryCondition implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * カテゴリ名
	 */
	private final String name;

	/**
	 * ソート
	 */
	private final CategorySort sort;

	/**
	 * オーダー
	 */
	private final Order order;

	/**
	 * ページ
	 */
	private final Integer page;

	/**
	 * ページサイズ
	 */
	private final Integer pageSize;

	public boolean hasName() {
		return name != null && !name.isEmpty();
	}

	public Integer getOffset() {
		return (page - 1) * pageSize;
	}

	public Integer getLimit() {
		return pageSize;
	}

	public String getOrderBy() {

		return sort.getSortKey() + " " + order.getOrder() + ", id ASC";

	}
}
