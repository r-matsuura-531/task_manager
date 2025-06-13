package com.example.taskmanager.presentation.category.search;

import com.example.taskmanager.domain.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class SearchCategoryResponseElem {

	private final String id;

	private final String name;
}
