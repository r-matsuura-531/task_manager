package com.example.taskmanager.presentation.task.search;

import com.example.taskmanager.domain.Order;
import com.example.taskmanager.domain.task.TaskSort;
import com.example.taskmanager.domain.task.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class SearchTaskRequest {

	private static final String DEFAULT_SORT = "id";
	private static final String DEFAULT_ORDER = "asc";
	private static final Integer DEFAULT_PAGE = 1;
	private static final Integer DEFAULT_PAGE_SIZE = 10;

	private String title;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;

	private TaskStatus status;

	private String categoryId;

	private TaskSort sort;

	private Order order;

	private Integer page;

	private Integer pageSize;

	public Integer getPage() {
		return page == null ? SearchTaskRequest.DEFAULT_PAGE : page;
	}

	public Integer getPageSize() {
		return pageSize == null ? SearchTaskRequest.DEFAULT_PAGE_SIZE : pageSize;
	}
}
