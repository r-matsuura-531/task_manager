package com.example.taskmanager.presentation.task.update;

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
public class UpdateTaskRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank()
	@Length(max = 50)
	private String title;

	@NotBlank()
	@Length(max = 200)
	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate dueDate;

	@AreValidTaskStatus
	@NotNull
	private TaskStatus status;

	@NotNull
	private String categoryId;
}
