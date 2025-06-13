package com.example.taskmanager.application.task.search;

import com.example.taskmanager.domain.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class SearchTaskQueryModel {

	private final String id;

	private final String title;

	private final String description;

	private final Date dueDate;

	private final TaskStatus status;

	private final String categoryName;
}
