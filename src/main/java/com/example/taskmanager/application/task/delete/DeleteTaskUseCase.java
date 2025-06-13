package com.example.taskmanager.application.task.delete;

import com.example.taskmanager.application.task.update.UpdateTaskInput;
import com.example.taskmanager.domain.task.Task;
import com.example.taskmanager.domain.task.TaskRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@AllArgsConstructor
public class DeleteTaskUseCase {

	private final TaskRepositry taskRepositry;

	/**
	 * タスクを登録する
	 *
	 * @param id タスクID
	 * */
	@Transactional
	public void handle(final String id) {
		taskRepositry.delete(id);
	}
}