package com.example.taskmanager.infrastructure.task;

import com.example.taskmanager.application.task.search.SearchTaskQueryModel;
import com.example.taskmanager.domain.task.TaskStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskInfrastructureMapper {

	/**
	 * SearchTaskEntityをSearchTaskQueryModelに変換する
	 * */
	public static SearchTaskQueryModel searchTaskEntityMapToSearchTaskQueryModel(SearchTaskEntity taskEntity) {
		return new SearchTaskQueryModel(
				taskEntity.getId(),
				taskEntity.getTitle(),
				taskEntity.getDescription(),
				taskEntity.getDueDate(),
				TaskStatus.of(taskEntity.getStatus()),
				taskEntity.getCategoryName()
		);
	}

	/**
	 * SearchTaskEntityをSearchTaskQueryModelに変換する
	 * */
	public static List<SearchTaskQueryModel> searchTaskEntitiesMapToSearchTaskQueryModels(List<SearchTaskEntity> taskEntities) {
		return taskEntities.stream()
				.map(TaskInfrastructureMapper::searchTaskEntityMapToSearchTaskQueryModel)
				.collect(Collectors.toList());
	}
}
