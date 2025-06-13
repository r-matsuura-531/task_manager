package com.example.taskmanager.domain.task;

import de.huxhorn.sulky.ulid.ULID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Task {

	private final TaskId id;

	private final String title;

	private final String description;

	private final Date dueDate;

	private final TaskStatus status;

	public static Task createNewTask(
			String title,
			String description,
			Date dueDate,
			TaskStatus status
	) {
		ULID ulid = new ULID();

		final TaskId taskId = new TaskId(ulid.nextULID());

		return new Task(
				taskId,
				title,
				description,
				dueDate,
				status
		);
	}

	public static Task createUpdateTask(
			String id,
			String title,
			String description,
			Date dueDate,
			TaskStatus status
	) {
		return new Task(
				new TaskId(id),
				title,
				description,
				dueDate,
				status
		);
	}
}
