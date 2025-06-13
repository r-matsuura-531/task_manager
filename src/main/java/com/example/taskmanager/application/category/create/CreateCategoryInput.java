package com.example.taskmanager.application.category.create;

import com.example.taskmanager.domain.task.TaskStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class CreateCategoryInput {

	private final String name;
}