package com.example.taskmanager.infrastructure.task_category;

import com.example.taskmanager.domain.task_category.TaskCategory;
import com.example.taskmanager.domain.task_category.TaskCategoryRepository;
import com.example.taskmanager.infrastructure.task.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TaskCategoryRepositoryImpl implements TaskCategoryRepository {

	private final TaskCategoryDao taskCategoryDao;

	@Override
	public void register(TaskCategory taskCategory) {
		TaskCategoryEntity taskCategoryEntity = taskCategoryMapToNewTaskCategoryEntity(taskCategory);
		int count = taskCategoryDao.insert(taskCategoryEntity);
		if (count != 1) {
			throw new IllegalStateException("Failed to insert task category.");
		}
	}

	@Override
	public void update(TaskCategory taskCategory) {
		TaskCategoryEntity taskCategoryEntity = taskCategoryMapToUpdatedTaskCategoryEntity(taskCategory);
		int count = taskCategoryDao.updateByTaskId(taskCategoryEntity);
		if (count != 1) {
			throw new IllegalStateException("Failed to update task category.");
		}
	}

	private TaskCategoryEntity taskCategoryMapToNewTaskCategoryEntity(TaskCategory taskCategory) {
		TaskCategoryEntity taskCategoryEntity = new TaskCategoryEntity();
		taskCategoryEntity.setId(taskCategory.getId().getValue());
		taskCategoryEntity.setCategoryId(taskCategory.getCategoryId().getValue());
		taskCategoryEntity.setTaskId(taskCategory.getTaskId().getValue());
		return taskCategoryEntity;
	}

	private TaskCategoryEntity taskCategoryMapToUpdatedTaskCategoryEntity(TaskCategory taskCategory) {
		TaskCategoryEntity taskCategoryEntity = new TaskCategoryEntity();
		taskCategoryEntity.setCategoryId(taskCategory.getCategoryId().getValue());
		taskCategoryEntity.setTaskId(taskCategory.getTaskId().getValue());
		return taskCategoryEntity;
	}
}
