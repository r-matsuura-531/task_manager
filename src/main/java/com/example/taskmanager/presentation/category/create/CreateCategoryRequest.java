package com.example.taskmanager.presentation.category.create;

import com.example.taskmanager.domain.task.TaskStatus;
import com.example.taskmanager.presentation.validation.AreValidTaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CreateCategoryRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank()
	@Length(max = 50)
	private String name;
}
