package com.example.taskmanager.presentation.validation.validator;


import com.example.taskmanager.domain.task.TaskStatus;
import com.example.taskmanager.presentation.task.create.CreateTaskRequest;
import com.example.taskmanager.presentation.validation.AreValidTaskStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class TaskStatusValidator implements ConstraintValidator<AreValidTaskStatus, TaskStatus> {

	@Override
	public boolean isValid(TaskStatus value, ConstraintValidatorContext context) {
		return isValidTaskStatus(value);
	}

	private boolean isValidTaskStatus(final TaskStatus status) {
		if (status == null) {
			return false;
		}

		return Arrays.asList(TaskStatus.values()).contains(status);
	}
}
