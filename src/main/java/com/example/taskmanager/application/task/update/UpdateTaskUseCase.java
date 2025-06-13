package com.example.taskmanager.application.task.update;

import com.example.taskmanager.application.task.create.CreateTaskInput;
import com.example.taskmanager.domain.task.Task;
import com.example.taskmanager.domain.task.TaskRepositry;
import com.example.taskmanager.domain.task_category.TaskCategory;
import com.example.taskmanager.domain.task_category.TaskCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@AllArgsConstructor
public class UpdateTaskUseCase {

	private final TaskRepositry taskRepositry;
	private final TaskCategoryRepository taskCategoryRepository;

	/**
	 * タスクを登録する
	 *
	 * @param input 登録するタスクデータ
	 * */
	@Transactional
	public void handle(final UpdateTaskInput input) {
		final Task updateTask = Task.createUpdateTask(
				input.getId(),
				input.getTitle(),
				input.getDescription(),
				Date.valueOf(input.getDueDate()),
				input.getStatus()
		);

		final TaskCategory taskCategory = TaskCategory.createUpdateTaskCategory(
				input.getCategoryId(),
				input.getId()
		);

		taskRepositry.update(updateTask);
		taskCategoryRepository.update(taskCategory);
	}
}