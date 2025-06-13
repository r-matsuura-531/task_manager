package com.example.taskmanager.infrastructure.task;

import com.example.taskmanager.domain.task.Task;
import com.example.taskmanager.domain.task.TaskRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TaskRepositoryImpl implements TaskRepositry {

	private final TaskDao taskDao;

	@Override
	public void register(Task task) {
		final TaskEntity taskEntity = taskMapToNewTaskEntity(task);
		int registerCount = taskDao.insert(taskEntity);
		if (registerCount != 1) {
			throw new IllegalStateException("タスクの登録に失敗");
		}
	}

	@Override
	public void update(Task task) {
		final TaskEntity taskEntity = taskMapToNewTaskEntity(task);
		int updateCount = taskDao.update(taskEntity);
		if (updateCount != 1) {
			throw new IllegalStateException("タスクの更新に失敗");
		}
	}

	@Override
	public void delete(String id) {
		final TaskEntity taskEntity = taskDao.findById(id);
		int deleteCount = taskDao.delete(taskEntity);
		if (deleteCount != 1) {
			throw new IllegalStateException("タスクの削除に失敗");
		}
	}

	private TaskEntity taskMapToNewTaskEntity(Task task) {
		final TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(task.getId().getValue());
		taskEntity.setTitle(task.getTitle());
		taskEntity.setDescription(task.getDescription());
		taskEntity.setDueDate(task.getDueDate());
		taskEntity.setStatus(task.getStatus().getValue());

		return taskEntity;
	}
}
