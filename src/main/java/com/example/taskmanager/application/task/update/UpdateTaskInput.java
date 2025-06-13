package com.example.taskmanager.application.task.update;

import com.example.taskmanager.domain.task.TaskStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UpdateTaskInput {

	private final String id;

	private final String title;

	private final String description;

	private final LocalDate dueDate;

	private final TaskStatus status;

	private final String categoryId;

}