package com.example.taskmanager.presentation.category.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UpdateCategoryRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank()
	@Length(max = 50)
	private String name;
}
