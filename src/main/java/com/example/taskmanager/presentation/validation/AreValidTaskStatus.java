package com.example.taskmanager.presentation.validation;

import com.example.taskmanager.presentation.validation.validator.TaskStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * タスクステータスバリデーション
 **/
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaskStatusValidator.class)
@Documented
public @interface AreValidTaskStatus {
	String message() default "{Invalid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
