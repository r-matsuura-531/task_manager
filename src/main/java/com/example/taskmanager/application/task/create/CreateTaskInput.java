package com.example.taskmanager.application.task.create;

import com.example.taskmanager.domain.task.TaskStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class CreateTaskInput {

	private final String title;

	private final String description;

	private final LocalDate dueDate;

	private final TaskStatus status;

	private final String categoryId;

}