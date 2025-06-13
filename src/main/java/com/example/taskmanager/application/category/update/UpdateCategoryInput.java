package com.example.taskmanager.application.category.update;

import com.example.taskmanager.domain.task.TaskStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UpdateCategoryInput {

	private final String id;

	private final String name;

}