package com.example.taskmanager.application.task.search;

import ch.qos.logback.core.util.StringUtil;
import com.example.taskmanager.domain.Order;
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
public class SearchTaskCondition implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * タイトル
	 */
	private final String title;

	/**
	 * 詳細
	 */
	private final String description;

	/**
	 * 期限日
	 */
	private final LocalDate dueDate;

	/**
	 * ステータス
	 */
	private final TaskStatus status;

	/**
	 * カテゴリID
	 */
	private final String categoryId;

	/**
	 * ソート
	 */
	private final TaskSort sort;

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

	public boolean hasTitle() {
		return !StringUtil.isNullOrEmpty(title);
	}

	public boolean hasDescription() {
		return !StringUtil.isNullOrEmpty(description);
	}

	public boolean hasDueDate() {
		return dueDate != null;
	}

	public boolean hasStatus() {
		return status != null;
	}

	public boolean hasCategoryId() {
		return categoryId != null;
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

	public String getFormattedDueDate() {
		if (this.getDueDate() == null) {
			return null;
		}

		String formattedDueDate = this.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		return formattedDueDate;
	}

}
