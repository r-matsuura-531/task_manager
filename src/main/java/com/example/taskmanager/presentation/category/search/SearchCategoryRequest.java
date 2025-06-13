package com.example.taskmanager.presentation.category.search;

import com.example.taskmanager.domain.Order;
import com.example.taskmanager.domain.category.CategorySort;
import com.example.taskmanager.domain.task.TaskSort;
import com.example.taskmanager.domain.task.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class SearchCategoryRequest {

	private static final String DEFAULT_SORT = "id";
	private static final String DEFAULT_ORDER = "asc";
	private static final Integer DEFAULT_PAGE = 1;
	private static final Integer DEFAULT_PAGE_SIZE = 10;

	private String name;

	private CategorySort sort;

	private Order order;

	private Integer page;

	private Integer pageSize;

	public Integer getPage() {
		return page == null ? SearchCategoryRequest.DEFAULT_PAGE : page;
	}

	public Integer getPageSize() {
		return pageSize == null ? SearchCategoryRequest.DEFAULT_PAGE_SIZE : pageSize;
	}
}
