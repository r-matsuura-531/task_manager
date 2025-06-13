package com.example.taskmanager.presentation.task.search;

import com.example.taskmanager.domain.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.thymeleaf.expression.Dates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor
@Getter
public class SearchTaskResponseElem {

	private final String id;

	private final String title;

	private final String description;

	private final LocalDate dueDate;

	private final TaskStatus status;

	private final String categoryName;
}
