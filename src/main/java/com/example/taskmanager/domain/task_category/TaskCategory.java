package com.example.taskmanager.domain.task_category;

import com.example.taskmanager.domain.category.CategoryId;
import com.example.taskmanager.domain.task.TaskId;
import de.huxhorn.sulky.ulid.ULID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskCategory {

	private final TaskCategoryId id;

	private final CategoryId categoryId;

	private final TaskId taskId;

	public static TaskCategory createNewTaskCategory(
			String categoryId,
			String taskId
	) {
		ULID ulid = new ULID();
		final TaskCategoryId taskCategoryId = new TaskCategoryId(ulid.nextULID());

		return new TaskCategory(
				taskCategoryId,
				new CategoryId(categoryId),
				new TaskId(taskId)
		);
	}

	public static TaskCategory createUpdateTaskCategory(
			String categoryId,
			String taskId
	) {
		return new TaskCategory(
				null,
				new CategoryId(categoryId),
				new TaskId(taskId)
		);
	}
}
