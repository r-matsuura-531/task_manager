package com.example.taskmanager.application.task.create;

import com.example.taskmanager.domain.category.CategoryId;
import com.example.taskmanager.domain.task.Task;
import com.example.taskmanager.domain.task.TaskRepositry;
import com.example.taskmanager.domain.task.TaskStatus;
import com.example.taskmanager.domain.task_category.TaskCategory;
import com.example.taskmanager.domain.task_category.TaskCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@AllArgsConstructor
public class CreateTaskUseCase {

	private final TaskRepositry taskRepositry;
	private final TaskCategoryRepository taskCategoryRepository;

	/**
	 * タスクを登録する
	 *
	 * @param input 登録するタスクデータ
	 * */
	@Transactional
	public void handle(final CreateTaskInput input) {

//		if (TaskStatus.DONE.equals(input.getStatus())) {
//			throw new IllegalArgumentException("DONE status is not allowed.");
//		}

		final Task newTask = Task.createNewTask(
				input.getTitle(),
				input.getDescription(),
				Date.valueOf(input.getDueDate()),
				input.getStatus()
		);

		final TaskCategory taskCategory = TaskCategory.createNewTaskCategory(
				input.getCategoryId(),
				newTask.getId().getValue()
		);

//		taskRepositry.register(newTask);
		taskRepositry.register(newTask);
		taskCategoryRepository.register(taskCategory);
	}
}