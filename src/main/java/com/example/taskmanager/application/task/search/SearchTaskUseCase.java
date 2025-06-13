package com.example.taskmanager.application.task.search;

import com.example.taskmanager.application.task.TaskQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchTaskUseCase {

	private final TaskQueryService taskQueryService;

	public SearchTasksQueryModel execute(SearchTaskCondition condition) {
		return taskQueryService.searchTaskQueryModel(condition);
	}
}
